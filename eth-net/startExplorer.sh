#!/bin/bash

cd explorer
echo "pass node_endpoint as parameter; ex: ./startExplorer.sh http://localhost:8545"
endpoint=$1 docker-compose up
