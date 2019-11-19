#!/bin/bash

cd webj3App/
mvn package
mv $1/webj3App/target/web3jApp-1.0-SNAPSHOT-jar-with-dependencies.jar $2
