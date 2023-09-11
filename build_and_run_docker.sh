#!/bin/bash

set -e
APP_NAME=${APP_NAME:-"text-validator-service"}
APP_VERSION=${APP_VERSION:-"0.0.1-SNAPSHOT"}

# Step 1: Build the project
echo "Building the project..."
./gradlew build

# Step 2: Build Docker image
echo "Building Docker image..."
docker build --build-arg APP_NAME="$APP_NAME" --build-arg APP_VERSION="$APP_VERSION" -t "$APP_NAME":"$APP_VERSION" .

# Step 3.1 Check if a container with the same name is already running and remove it
EXISTING_CONTAINER=$(docker ps -aq -f name="${APP_NAME}")
if [ -n "$EXISTING_CONTAINER" ]; then
  docker rm -f "${EXISTING_CONTAINER}"
fi

# Step 3.2: Run container
echo "Running container..."
docker run -d --name "${APP_NAME}"-"${APP_VERSION}" -p 8080:8080 "${APP_NAME}":"${APP_VERSION}"

# Step 4: Display api URLs
echo "The service API is available at http://localhost:8080/api"
echo "The Swagger UI is available at http://localhost:8080/swagger-ui"
echo "The API docs is available at http://localhost:8080/api-docs"