package com.contentfarm.kafka.springboot.starter;

import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.kafka.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public class TestingKafkaTestContainer {
    private static final String IMAGE_NAME = "apache/kafka:4.0.0-rc0";

    @ServiceConnection
    @Container //
    public static KafkaContainer container = new KafkaContainer(
            DockerImageName.parse(IMAGE_NAME))
            .withEnv("KAFKA_LISTENERS", "PLAINTEXT://:9092,BROKER://:9093,CONTROLLER://:9094");
}
