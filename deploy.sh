#!/bin/bash
set -e

docker-compose up -d rabbitMq

sleep 12

docker-compose up -d