#!/bin/bash

set -e

echo "Building the Maven project..."
mvn -f ./API/pom.xml clean package

echo "Building and running Docker containers..."
docker compose build
docker compose up -d

mvn -f ./Client test
