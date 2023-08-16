#!/bin/bash
set -e

VERSION=1.0
NS=cinema-reservation
DIR=$(pwd)

# Push images to minikube registry
eval $(minikube docker-env)

for service in 'payment-service' 'seat-booking-service';
  do
    cd "${DIR}/${service}" || exit
    tag="${NS}/${service}:${VERSION}"
    executed_cmd="docker build -t ${tag} ."
    echo "${executed_cmd}" && eval "${executed_cmd}"
  done