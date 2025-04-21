#!/bin/bash
set -e

echo "[node1-init] $(date) Starting init script..."

# Wait for PostgreSQL to accept local connections
echo "[node1-init] $(date) Waiting for PostgreSQL to accept local connections..."
until pg_isready -h localhost -U postgres; do
  sleep 1
done
echo "[node1-init] $(date) PostgreSQL is ready with local connections."

# Ensure listen_addresses = '*'
echo "[node1-init] $(date) Setting listen_addresses = '*'..."
psql -U postgres -c "ALTER SYSTEM SET listen_addresses = '*';"
psql -U postgres -c "SELECT pg_reload_conf();"

# Wait for TCP interface to be available
echo "[node1-init] $(date) Waiting for TCP access via postgres1..."
until pg_isready -h localhost -U bdr -d bdrdb; do
  sleep 1
done
echo "[node1-init] $(date) postgres1 TCP access confirmed."



# Run finalize script in the background
echo "[node1-init] $(date) Launching finalize script in background..."
bash /finalize-node1.sh &

echo "[node1-init] $(date) Init script done. Finalize continues in background."
