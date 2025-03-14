package com.contentfarm.blog.service.persistence;

import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Duration;

@Testcontainers
public interface TestPostgreSQLContainer {
    @ServiceConnection
    @Container
    PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:17.4")
            .withDatabaseName("testing")
            .withUsername("postgres")
            .withPassword("postgres")
            .withExposedPorts(5432)
            .withCreateContainerCmdModifier(cmd -> cmd.withHostConfig(
                    new HostConfig().withPortBindings(new PortBinding(Ports.Binding.bindPort(5433), new ExposedPort(5432)))
            ));
}
