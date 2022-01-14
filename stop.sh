#!/bin/bash
set -e

docker-compose down
docker rm -f /e2e-client