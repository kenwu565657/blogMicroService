package com.contentfarm.kafka.springboot.starter.producer;

public interface IBlogPostKafkaMessageProducer {
    void send(String topic, String message);
}
