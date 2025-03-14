package com.contentfarm.blog.service.message.queue.producer;

import com.contentfarm.command.blogpost.AdminIndexBlogPostCommand;
import com.contentfarm.kafka.springboot.starter.constant.ContentFarmKafkaTopicConstant;
import com.contentfarm.kafka.springboot.starter.producer.IBlogPostKafkaMessageProducer;
import com.contentfarm.utils.ContentFarmJsonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BlogPostMessageQueueProducer {
    private final IBlogPostKafkaMessageProducer blogPostKafkaMessageProducer;

    public void sendAddBlogPostCompleteMessage(AdminIndexBlogPostCommand adminIndexBlogPostCommand) {
        String serializedJsonString = ContentFarmJsonUtils.serializeToJsonString(adminIndexBlogPostCommand);
        blogPostKafkaMessageProducer
                .send(
                        ContentFarmKafkaTopicConstant.CONTENT_FARM_KAFKA_ADD_BLOGPOST_COMPLETE_TOPIC,
                        serializedJsonString
                );
    }
}
