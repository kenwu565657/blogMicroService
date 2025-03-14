package com.contentfarm.search.mq.publisher;

import com.contentfarm.kafka.springboot.starter.constant.ContentFarmKafkaTopicConstant;
import com.contentfarm.kafka.springboot.starter.producer.IBlogPostKafkaMessageProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BlogPostMessageQueueEventPublisher {
    private final IBlogPostKafkaMessageProducer blogPostMessageProducer;

    public void sendBlogPostDeIndexFinishEvent(String message) {
        blogPostMessageProducer.send(ContentFarmKafkaTopicConstant.CONTENT_FARM_KAFKA_DE_INDEX_BLOGPOST_COMPLETE_TOPIC, message);
    }
}
