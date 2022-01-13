#!/bin/bash
set -e

pushd EndToEndTestClient
./build.sh
popd

# Startup all services for this service
./deploy.sh 
sleep 5

# Test the services
./test.sh

# Cleanup the build images
docker image prune -f

