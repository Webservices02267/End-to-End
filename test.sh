#!/bin/bash
set -e

docker run --name e2e-client --network=e2e-network --entrypoint /home/app/run.sh e2e-client