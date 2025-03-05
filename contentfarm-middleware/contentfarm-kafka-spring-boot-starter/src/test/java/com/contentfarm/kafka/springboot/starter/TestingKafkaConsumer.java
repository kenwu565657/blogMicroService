package com.contentfarm.kafka.springboot.starter;

import com.contentfarm.kafka.springboot.starter.annotation.BlogPostCommandKafkaTopicListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;

/**
 * copied from <a href="https://www.baeldung.com/spring-boot-kafka-testing">...</a>
 *
 */
@Component
public class TestingKafkaConsumer {
    private final Logger logger = LoggerFactory.getLogger(TestingKafkaConsumer.class);
    private CountDownLatch latch = new CountDownLatch(1);
    private final ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
    private String payload;

    @BlogPostCommandKafkaTopicListener
    public void receive(String payload) {
        logger.info("Received record: {}", payload);
        queue.add(payload);
        this.payload = payload;
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public String getPayload() {
        return payload;
    }

    public void resetLatch() {
        latch = new CountDownLatch(1);
    }

    public Flux<String> subsribe() {
        return Flux.fromIterable(queue);
    }
}
