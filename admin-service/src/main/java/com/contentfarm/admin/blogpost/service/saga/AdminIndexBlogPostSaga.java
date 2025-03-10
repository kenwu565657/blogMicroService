package com.contentfarm.admin.blogpost.service.saga;

import com.contentfarm.command.blogpost.AdminIndexBlogPostCommand;
import com.contentfarm.kafka.springboot.starter.constant.ContentFarmKafkaGroupConstant;
import com.contentfarm.kafka.springboot.starter.constant.ContentFarmKafkaTopicConstant;
import com.contentfarm.kafka.springboot.starter.producer.IBlogPostKafkaMessageProducer;
import com.contentfarm.utils.ContentFarmJsonUtils;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

//todo: implement saga rollback
@Component
public class AdminIndexBlogPostSaga {
    private final IBlogPostKafkaMessageProducer blogPostMessageProducer;

    public AdminIndexBlogPostSaga(IBlogPostKafkaMessageProducer blogPostMessageProducer) {
        this.blogPostMessageProducer = blogPostMessageProducer;
    }

    public void indexBlogPost(AdminIndexBlogPostCommand adminIndexBlogPostCommand) {
        String serializedJsonString = ContentFarmJsonUtils.serializeToJsonString(adminIndexBlogPostCommand);
        blogPostMessageProducer.send(ContentFarmKafkaTopicConstant.CONTENT_FARM_KAFKA_ADD_BLOGPOST_TOPIC, serializedJsonString);
    }

    @KafkaListener(groupId = ContentFarmKafkaGroupConstant.CONTENT_FARM_KAFKA_GROUP, topics = ContentFarmKafkaTopicConstant.CONTENT_FARM_KAFKA_ADD_BLOGPOST_COMPLETE_TOPIC)
    public void indexBlogPost(String message) {
        blogPostMessageProducer.send(ContentFarmKafkaTopicConstant.CONTENT_FARM_KAFKA_INDEX_BLOGPOST_TOPIC, message);
    }
}
