package com.contentfarm.kafka.springboot.starter.producer.impl;

import com.contentfarm.kafka.springboot.starter.TestingConfiguration;
import com.contentfarm.kafka.springboot.starter.TestingKafkaConsumer;
import com.contentfarm.kafka.springboot.starter.producer.IBlogPostKafkaMessageProducer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@EnableKafka
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = TestingConfiguration.class)
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
class BlogPostMessageProducerTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    IBlogPostKafkaMessageProducer blogPostMessageProducer;

    @Autowired
    TestingKafkaConsumer testingKafkaConsumer;

    @BeforeAll
    void setUp() {

    }

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

    @Test
    void send_2Message() {
        String data = "TestingMessage";
        blogPostMessageProducer.send(data);

        String data2 = "TestingMessage2";
        blogPostMessageProducer.send(data2);

        Flux<String> receivedMessageQueue = testingKafkaConsumer.subsribe().delayElements(Duration.ofSeconds(5));
        StepVerifier.create(receivedMessageQueue)
                //.expectNext(data)
                //.expectNext(data2)
                .expectComplete()
                .verify();
    }
}
