#!/bin/bash
set -euo pipefail

SCRIPT_DIR=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )
VOLUMES_DIR="${SCRIPT_DIR}/../infrastructure/docker-compose/volumes"
DOCKER_COMPOSE_DIR="${SCRIPT_DIR}/../infrastructure/docker-compose"


### create volumes and apply permissions if does not exist ###
if [ ! -d  "${VOLUMES_DIR}" ]
then
    echo "creating volume for kafka: ${VOLUMES_DIR}/kafka"
    mkdir -p "${VOLUMES_DIR}/kafka/broker-1"
    mkdir -p "${VOLUMES_DIR}/kafka/broker-2"
    mkdir -p "${VOLUMES_DIR}/kafka/broker-3"

    echo "creating volume for zookeeper: ${VOLUMES_DIR}/zookeeper"
     mkdir -p "${VOLUMES_DIR}/zookeeper/data"
     mkdir -p "${VOLUMES_DIR}/zookeeper/transactions"

    echo "applying volume permissions"
    chown -R 1000:1000 "${VOLUMES_DIR}"
fi

cd "${DOCKER_COMPOSE_DIR}" || exit

docker compose -f "./common/network.yml" -f "./infra/kafka_cluster.yml" --env-file .env up -d

docker compose -f "./common/network.yml" -f "./infra/postgres.yml" --env-file .env up -d
