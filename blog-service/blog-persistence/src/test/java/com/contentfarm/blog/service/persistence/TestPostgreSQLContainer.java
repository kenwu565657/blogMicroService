package com.contentfarm.blog.service.persistence;

import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public interface TestPostgreSQLContainer {
    @ServiceConnection
    @Container
    PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:17.4");
}
