#!/bin/bash
set -e

echo "[init] Waiting for node2 PostgreSQL to be ready..."
until psql -U bdr -d bdrdb -c "SELECT 1;" > /dev/null 2>&1; do
  echo "[init] Still waiting for node2 Postgres..."
  sleep 2
done

echo "[init] Creating required extensions on node2..."
psql -U bdr -d bdrdb <<-EOSQL
  CREATE EXTENSION IF NOT EXISTS btree_gist;
  CREATE EXTENSION IF NOT EXISTS bdr;
EOSQL

echo "[init] Forcing PostgreSQL to reload pg_hba.conf from mounted file..."
psql -U bdr -d bdrdb -c "ALTER SYSTEM SET hba_file = '/var/lib/postgresql/pg_hba.conf';"
psql -U bdr -d bdrdb -c "SELECT pg_reload_conf();"

# Run BDR join logic in background
(
  echo "[join] Waiting for BDR background workers to start on node2..."
  until psql -U bdr -d bdrdb -c "SELECT * FROM pg_stat_activity WHERE application_name LIKE 'bdr%';" | grep -q 'bdr'; do
    echo "[join] BDR workers not ready yet..."
    sleep 5
  done

  echo "[join] Waiting for postgres1 to be ready..."
  until pg_isready -h postgres1 -U bdr -d bdrdb; do
    echo "[join] postgres1 not ready yet..."
    sleep 3
  done

  # Final buffer to ensure stability
  sleep 5

  # 🔍 Add debugging queries here
  echo "[join] Checking current BDR nodes and replication slots..."
  psql -U bdr -d bdrdb -c "SELECT * FROM bdr.bdr_nodes;"
  psql -U bdr -d bdrdb -c "SELECT * FROM pg_replication_slots;"

  echo "[join] Attempting to join BDR group..."
  until psql -U bdr -d bdrdb -c "
    SELECT bdr.bdr_group_join(
      local_node_name := 'postgres2',
      node_external_dsn := 'host=postgres2 port=5432 dbname=bdrdb user=bdr password=bdrpass',
      join_using_dsn := 'host=postgres1 port=5432 dbname=bdrdb user=bdr password=bdrpass'
    );
  " > /dev/null 2>&1; do
    echo "[join] bdr_group_join failed — retrying in 5s..."
    sleep 5
  done

  echo "[join] BDR group join completed successfully ✅"
) &

