#!/bin/bash

IMAGE_NAME="dux-software-prueba-tecnica"

docker rm -f IMAGE_NAME 2>/dev/null

docker build -t $IMAGE_NAME .

docker run -p 8080:8080 --name $IMAGE_NAME $IMAGE_NAME