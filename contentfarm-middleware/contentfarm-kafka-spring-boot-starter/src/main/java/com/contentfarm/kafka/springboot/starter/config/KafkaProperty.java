package com.contentfarm.kafka.springboot.starter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "kafka")
public class KafkaProperty {
    private String producerBootstrapServer = "localhost:9092";
    private String consumerBootstrapServer = "localhost:9092";

    public String getProducerBootstrapServer() {
        return producerBootstrapServer;
    }

    public void setProducerBootstrapServer(String producerBootstrapServer) {
        this.producerBootstrapServer = producerBootstrapServer;
    }

    public String getConsumerBootstrapServer() {
        return consumerBootstrapServer;
    }

    public void setConsumerBootstrapServer(String consumerBootstrapServer) {
        this.consumerBootstrapServer = consumerBootstrapServer;
    }
}
