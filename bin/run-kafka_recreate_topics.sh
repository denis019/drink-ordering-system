#!/bin/bash
set -euo pipefail

SCRIPT_DIR=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )
DOCKER_COMPOSE_DIR="${SCRIPT_DIR}/../infrastructure/docker-compose"

cd "${DOCKER_COMPOSE_DIR}" || exit

docker-compose -f "./common/network.yml" -f "./infra/kafka_init.yml" --env-file .env up