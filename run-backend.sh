#!/bin/bash

CONTAINER_NAME="base-api-container"
IMAGE_NAME="base-api"

docker rm -f $CONTAINER_NAME 2>/dev/null

docker build -t $IMAGE_NAME .

docker run -p 8080:8080 --name $CONTAINER_NAME $IMAGE_NAME