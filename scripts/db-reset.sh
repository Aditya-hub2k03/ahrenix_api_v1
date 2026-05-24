#!/bin/bash

echo "Resetting AHrenix database..."

docker compose down

docker volume rm ahrenix_postgres_data

docker compose up -d postgres

echo "Database reset complete."