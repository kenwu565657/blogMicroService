package com.contentfarm.kafka.springboot.starter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Objects;

@ConfigurationProperties(prefix = "kafka")
public class KafkaProperty {
    private String producerBootstrapServer;
    private String consumerBootstrapServer;

    public String getProducerBootstrapServer() {
        return producerBootstrapServer;
    }

    public String getProducerBootstrapServerOrElseDefaultValue() {
        String producerBootstrapServerConfigValue = producerBootstrapServer;
        return Objects.requireNonNullElse(producerBootstrapServerConfigValue, "localhost:9092");
    }

    public void setProducerBootstrapServer(String producerBootstrapServer) {
        this.producerBootstrapServer = producerBootstrapServer;
    }

    public String getConsumerBootstrapServer() {
        return consumerBootstrapServer;
    }

    public String getConsumerBootstrapServerOrElseDefaultValue() {
        String consumerBootstrapServerConfigValue = consumerBootstrapServer;
        return Objects.requireNonNullElse(consumerBootstrapServerConfigValue, "localhost:9092");
    }

    public void setConsumerBootstrapServer(String consumerBootstrapServer) {
        this.consumerBootstrapServer = consumerBootstrapServer;
    }
}
