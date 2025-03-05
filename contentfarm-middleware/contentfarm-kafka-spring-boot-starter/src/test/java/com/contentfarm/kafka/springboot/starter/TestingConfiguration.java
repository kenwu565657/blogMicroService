package com.contentfarm.kafka.springboot.starter;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.contentfarm.kafka.springboot.starter")
public class TestingConfiguration {
    @Bean
    TestingKafkaConsumer testingKafkaConsumer() {
        return new TestingKafkaConsumer();
    }
}
