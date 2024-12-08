# Spring Boot Application

This is a Spring Boot application for a crypto recommendation service . This README provides information on how to run the application.

## Prerequisites

Before running this application, ensure that you have the following installed:
- Docker

## Getting Started

To run the application, follow these steps:

1. Clone this repository to your local machine:

   ```bash
   git clone git@github.com:LChatzimi/crypto.git
   ```
2.   Navigate into project folder and run:
   ```bash
   docker compose up --build --force-recreate
   ```

## Access API
By the time the application is deployed successfully, you can access the swagger-ui documentation at http://localhost:8080/cryptoRecommendationService/swagger-ui/index.html#/ .
Endpoints under /cryptos require no authentication, while the endpoint under /cryptoAdmin (that you can use to create data) require basic authentication with username=admin, password=admin.