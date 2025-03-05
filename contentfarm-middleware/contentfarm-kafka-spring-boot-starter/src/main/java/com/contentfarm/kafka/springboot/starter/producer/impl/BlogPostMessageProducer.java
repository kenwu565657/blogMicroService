package com.contentfarm.kafka.springboot.starter.producer.impl;

import com.contentfarm.kafka.springboot.starter.constant.ContentFarmKafkaTopicConstant;
import com.contentfarm.kafka.springboot.starter.producer.IBlogPostKafkaMessageProducer;
import org.springframework.kafka.core.KafkaTemplate;

public class BlogPostMessageProducer implements IBlogPostKafkaMessageProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public BlogPostMessageProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(String message) {
        kafkaTemplate.send(ContentFarmKafkaTopicConstant.CONTENT_FARM_KAFKA_TOPIC, message);
    }
}
