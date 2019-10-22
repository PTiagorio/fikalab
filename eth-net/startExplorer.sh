#!/bin/bash

cd explorer
echo "pass node_endpoint as parameter; ex: ./startExplorer.sh http://localhost:8545"
NODE_ENDPOINT=$1 docker-compose up
