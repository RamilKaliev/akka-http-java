#!/bin/bash

set -e

DOCKER_IMAGE_NAME=holydocker/akka-http-java

DEV_REMOTE_IP=0.0.0.0:5000
DEV_VERSION=dev

QA_REMOTE_IP=0.0.0.0:5000
QA_VERSION=qa

PROD_REMOTE_IP=0.0.0.0:5000
PROD_VERSION=v0.1.0

case $1 in
    "build")
        sbt clean compile dist
        docker build --no-cache -t $DOCKER_IMAGE_NAME .
        docker tag $DOCKER_IMAGE_NAME:latest $REMOTE_IP/$DOCKER_IMAGE_NAME:$QA_VERSION
    ;;
    "dev")
        sbt clean compile dist
        docker build --no-cache -t $DOCKER_IMAGE_NAME .
        docker tag $DOCKER_IMAGE_NAME:latest $DEV_REMOTE_IP/$DOCKER_IMAGE_NAME:$DEV_VERSION
        docker push $DEV_REMOTE_IP/$DOCKER_IMAGE_NAME:$DEV_VERSION
    ;;
    "dev-push")
        docker push $DEV_REMOTE_IP/$DOCKER_IMAGE_NAME:$DEV_VERSION
    ;;
    "stage")
        sbt clean compile dist
        docker build --no-cache -t $DOCKER_IMAGE_NAME .
        docker tag $DOCKER_IMAGE_NAME:latest $REMOTE_IP/$DOCKER_IMAGE_NAME:$QA_VERSION
        docker push $REMOTE_IP/$DOCKER_IMAGE_NAME:$QA_VERSION
    ;;
    "prod")
        sbt clean compile dist
        docker build --no-cache -t $DOCKER_IMAGE_NAME .
        docker tag $DOCKER_IMAGE_NAME:latest $REMOTE_IP/$DOCKER_IMAGE_NAME:$PROD_VERSION
        docker push $REMOTE_IP/$DOCKER_IMAGE_NAME:$PROD_VERSION
    ;;
esac
