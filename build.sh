#!/bin/bash

PARAM=$1

if [[ "$PARAM" == "maven" || "$PARAM" == "all" ]]; then
    mvn clean install -DskipTests
fi

if [[ "$PARAM" == "docker" || "$PARAM" == "all" ]]; then
    docker build -t jaypark00/order:msa_v2 --build-arg JAR_FILE=order-0.0.1-SNAPSHOT.jar .
fi

if [[ "$PARAM" == "push" || "$PARAM" == "all" ]]; then
    docker push jaypark00/order:msa_v2
fi

