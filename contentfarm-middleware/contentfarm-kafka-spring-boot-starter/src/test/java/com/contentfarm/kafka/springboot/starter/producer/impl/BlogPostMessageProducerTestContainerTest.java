package com.contentfarm.kafka.springboot.starter.producer.impl;

import com.contentfarm.kafka.springboot.starter.TestingConfiguration;
import com.contentfarm.kafka.springboot.starter.TestingKafkaConsumer;
import com.contentfarm.kafka.springboot.starter.TestingKafkaTestContainer;
import com.contentfarm.kafka.springboot.starter.producer.IBlogPostKafkaMessageProducer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.context.ImportTestcontainers;

import java.util.concurrent.TimeUnit;

@ImportTestcontainers(TestingKafkaTestContainer.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = TestingConfiguration.class)
public class BlogPostMessageProducerTestContainerTest {
    @Autowired
    IBlogPostKafkaMessageProducer blogPostMessageProducer;

    @Autowired
    TestingKafkaConsumer testingKafkaConsumer;

    @Test
    void send() throws InterruptedException {
        String data = "TestingMessage";
        blogPostMessageProducer.send(data);

        boolean messageConsumed = testingKafkaConsumer.getLatch().await(20, TimeUnit.SECONDS);
        Assertions.assertTrue(messageConsumed);
        Assertions.assertEquals(data, testingKafkaConsumer.getPayload());

        testingKafkaConsumer.resetLatch();
        String data2 = "TestingMessage2";
        blogPostMessageProducer.send(data2);

        messageConsumed = testingKafkaConsumer.getLatch().await(10, TimeUnit.SECONDS);
        Assertions.assertTrue(messageConsumed);
        Assertions.assertEquals(data2, testingKafkaConsumer.getPayload());
    }
}
