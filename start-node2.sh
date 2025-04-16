#!/bin/bash
set -e

# Wait until PostgreSQL is fully started
until pg_isready -h localhost -p 5432; do
  echo "Waiting for postgres..."
  sleep 1
done

# Enable listen_addresses for Docker networking
echo "listen_addresses = '*'" >> /var/lib/postgresql/data/postgresql.conf

# Allow replication from other nodes
cat >> /var/lib/postgresql/data/pg_hba.conf <<EOF
host    all             all             0.0.0.0/0            trust
host    replication     all             0.0.0.0/0            trust
EOF

# Reload Postgres
pg_ctl -D "$PGDATA" reload

# Create required extensions
psql -U postgres -d bdrdb <<EOF
CREATE EXTENSION IF NOT EXISTS btree_gist;
CREATE EXTENSION IF NOT EXISTS bdr;
EOF

# Wait for BDR to be ready
echo "Waiting for BDR schema to be available..."
until psql -U postgres -d bdrdb -c "SELECT bdr.bdr_version();" > /dev/null 2>&1; do
  echo "Waiting for BDR to be ready..."
  sleep 2
done

# JOIN node2 to node1
echo "Joining node2 to BDR group..."
psql -U postgres -d bdrdb <<EOF
SELECT bdr.bdr_group_join(
  local_node_name := 'node2',
  node_external_dsn := 'host=pg_node2 port=5432 dbname=bdrdb user=postgres password=postgres',
  join_using_dsn := 'host=pg_node1 port=5432 dbname=bdrdb user=postgres password=postgres'
);
EOF
