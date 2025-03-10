package com.contentfarm.admin.blogpost.service.saga;

import com.contentfarm.command.blogpost.AdminDeleteBlogPostCommand;
import com.contentfarm.kafka.springboot.starter.constant.ContentFarmKafkaGroupConstant;
import com.contentfarm.kafka.springboot.starter.constant.ContentFarmKafkaTopicConstant;
import com.contentfarm.kafka.springboot.starter.producer.IBlogPostKafkaMessageProducer;
import com.contentfarm.utils.ContentFarmJsonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

//todo: implement saga rollback
@Component
@RequiredArgsConstructor
public class AdminDeleteBlogPostSaga {
    private final IBlogPostKafkaMessageProducer blogPostMessageProducer;

    public void deleteBlogPostIndex(AdminDeleteBlogPostCommand adminDeleteBlogPostCommand) {
        String serializedJsonString = ContentFarmJsonUtils.serializeToJsonString(adminDeleteBlogPostCommand);
        blogPostMessageProducer.send(ContentFarmKafkaTopicConstant.CONTENT_FARM_KAFKA_DE_INDEX_BLOGPOST_TOPIC, serializedJsonString);
    }

    @KafkaListener(groupId = ContentFarmKafkaGroupConstant.CONTENT_FARM_KAFKA_GROUP, topics = ContentFarmKafkaTopicConstant.CONTENT_FARM_KAFKA_DE_INDEX_BLOGPOST_COMPLETE_TOPIC)
    public void listenDeleteBlogPostIndexFinish(String message) {
        deleteBlogPostRecord(message);
    }

    public void deleteBlogPostRecord(String message) {
        blogPostMessageProducer.send(ContentFarmKafkaTopicConstant.CONTENT_FARM_KAFKA_DELETE_BLOGPOST_TOPIC, message);
    }
}
