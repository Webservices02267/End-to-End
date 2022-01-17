#!/bin/bash
set -e

./stop.sh

# Startup all services for this service
./deploy.sh 
sleep 5


pushd Client
./build.sh
popd


# Test the services
./test.sh

# Cleanup the build images
docker image prune -f

