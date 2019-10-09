#!/bin/bash

pm2 start app.json
set WS_SECRET=secret && npm start
