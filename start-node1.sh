#!/bin/bash
set -e

echo "Waiting for PostgreSQL to be ready..."
until pg_isready -U bdr; do
  sleep 1
done

# ⏳ Optional extra wait to ensure full internal readiness
sleep 5

echo "Creating BDR group..."
psql -v ON_ERROR_STOP=1 --username "bdr" --dbname "bdrdb" <<-EOSQL
  CREATE EXTENSION IF NOT EXISTS btree_gist;
  CREATE EXTENSION IF NOT EXISTS bdr;

  SELECT bdr.bdr_group_create(
      local_node_name := 'node1',
      node_external_dsn := 'host=postgres1 port=5432 dbname=bdrdb user=bdr password=bdrpass'
  );
EOSQL
