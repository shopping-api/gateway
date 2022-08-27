# Gateway
[![Build and test](https://github.com/groot-mg/gateway/actions/workflows/gateway-ci.yml/badge.svg)](https://github.com/groot-mg/gateway/actions/workflows/gateway-ci.yml) [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=shopping-api_gateway&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=shopping-api_gateway) [![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](https://github.com/groot-mg/gateway/blob/main/LICENSE)

API Gateway is a tool that provides routing mechanisms to microservices applications as a way of hiding multiple services behind a single facade.

It provides a security layer, and it is the user entry point. All requests coming to the application will go thought the API Gateway.

It provides load balancing across the registered service instances for the same service.

## How to run

API Gateway should run together with the Service discovery and other services, to run this project, please see [docker-local-setup](https://github.com/groot-mg/docker-local-setup).