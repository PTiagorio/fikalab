#!/bin/bash

cd eth-net-intelligence-api
pm2 start app.json

cd ../eth-netstats
npm start
