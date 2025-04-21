#!/bin/bash
set -e

echo "[finalize-node1] $(date) Waiting for postgres1 TCP readiness..."

# Wait for TCP to be available on the container's actual hostname
until pg_isready -h postgres1 -U bdr -d bdrdb; do
  echo "[finalize-node1] Still waiting on TCP..."
  sleep 2
done

echo "[finalize-node1] TCP access confirmed — running extensions and BDR group creation..."

psql -U bdr -d bdrdb <<-EOSQL
  CREATE EXTENSION IF NOT EXISTS btree_gist;
  CREATE EXTENSION IF NOT EXISTS bdr;
EOSQL

# Give BDR some time to settle before creating group
sleep 3

psql -U bdr -d bdrdb <<-EOSQL
  SELECT bdr.bdr_group_create(
    local_node_name := 'node1',
    node_external_dsn := 'host=postgres1 port=5432 dbname=bdrdb user=bdr password=bdrpass'
  );
EOSQL

echo "[finalize-node1] $(date) BDR group created successfully ✅"
