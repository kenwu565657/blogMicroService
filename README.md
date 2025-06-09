# Blog Microservice
This project is a SpringCloud project served as backend of a blog web application. The main propose is to practice the latest microservice technology, including SpringCloud Gateway, Spring Authorization Server, Spring Cloud Netflix, Spring Cloud OpenFeign, Spring for Apache Kafka and more...

# System Architecture
![System Architecture](/readme-file/system-architecture.png)

# How To Start
## Step 1. Start Middleware (elasticsearch, kafka, postgres, redis)
```shell
cd docker-compose/docker-compose-middleware
docker-compose up -d
```

## Step 2. Start Micro Service (gateway, eureka, cronjob, auth-server...)
```shell
cd docker-compose/docker-compose-microservice
docker compose --env-file .env --env-file secret.env up -d
```
