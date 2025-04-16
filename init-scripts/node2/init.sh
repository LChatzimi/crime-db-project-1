#!/bin/bash
set -e

echo "Waiting for node2 PostgreSQL to be ready..."
until pg_isready -U bdr; do
  sleep 1
done

echo "Waiting for postgres1 (node1) to be ready..."
until pg_isready -h postgres1 -U bdr -d bdrdb; do
  echo "Still waiting for postgres1..."
  sleep 2
done

sleep 5

echo "Joining BDR group (node2)..."
psql -U bdr -d bdrdb <<-EOSQL
  CREATE EXTENSION IF NOT EXISTS btree_gist;
  CREATE EXTENSION IF NOT EXISTS bdr;

  SELECT bdr.bdr_group_join(
    local_node_name := 'node2',
    node_external_dsn := 'host=postgres2 port=5432 dbname=bdrdb user=bdr password=bdrpass',
    join_using_dsn := 'host=postgres1 port=5432 dbname=bdrdb user=bdr password=bdrpass'
  );
EOSQL

SELECT bdr.bdr_group_join(local_node_name := 'node2', node_external_dsn := 'host=localhost port=5432 db
name=bdrdb user=bdr password=bdrpass', join_using_dsn := 'host=postgres1 port=5432 dbname=bdrdb user=bdr password=bdrpass');