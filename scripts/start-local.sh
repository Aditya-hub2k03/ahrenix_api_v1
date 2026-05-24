#!/bin/bash

echo "Starting AHrenix local infrastructure..."

docker compose up -d

echo "Waiting for services to start..."

sleep 20

echo "Starting Spring Boot backend..."

mvn spring-boot:run