#!/bin/bash
set -e

echo "Waiting for node1 PostgreSQL to be ready..."
until pg_isready -U bdr; do
  sleep 1
done

sleep 5

echo "Creating BDR group (node1)..."
psql -U bdr -d bdrdb <<-EOSQL
  CREATE EXTENSION IF NOT EXISTS btree_gist;
  CREATE EXTENSION IF NOT EXISTS bdr;

  SELECT bdr.bdr_group_create(
    local_node_name := 'node1',
    node_external_dsn := 'host=localhost port=5432 dbname=bdrdb user=bdr password=bdrpass'
  );
EOSQL
