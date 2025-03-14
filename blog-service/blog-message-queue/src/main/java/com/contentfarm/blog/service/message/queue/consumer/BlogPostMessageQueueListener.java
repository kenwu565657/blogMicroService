package com.contentfarm.blog.service.message.queue.consumer;

import com.contentfarm.blog.service.message.queue.mapper.BlogPostMessageQueueMapper;
import com.contentfarm.blog.service.message.queue.producer.BlogPostMessageQueueProducer;
import com.contentfarm.blog.service.domain.aggregateroot.blogpost.BlogPostDomainModel;
import com.contentfarm.blog.service.domain.inputport.mq.blogpost.IBlogPostMessageQueueDomainService;
import com.contentfarm.blog.service.domain.inputport.mq.blogpost.IBlogPostMessageQueueListener;
import com.contentfarm.command.blogpost.AdminDeleteBlogPostCommand;
import com.contentfarm.command.blogpost.AdminIndexBlogPostCommand;
import com.contentfarm.kafka.springboot.starter.constant.ContentFarmKafkaGroupConstant;
import com.contentfarm.kafka.springboot.starter.constant.ContentFarmKafkaTopicConstant;
import com.contentfarm.utils.ContentFarmJsonUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class BlogPostMessageQueueListener implements IBlogPostMessageQueueListener {
    private final Logger logger = LoggerFactory.getLogger(BlogPostMessageQueueListener.class);
    private final IBlogPostMessageQueueDomainService blogPostDomainService;
    private final BlogPostMessageQueueProducer blogPostMessageQueueProducer;
    private final BlogPostMessageQueueMapper blogPostMessageQueueMapper;

    @Transactional
    @KafkaListener(groupId = ContentFarmKafkaGroupConstant.CONTENT_FARM_KAFKA_GROUP, topics = ContentFarmKafkaTopicConstant.CONTENT_FARM_KAFKA_ADD_BLOGPOST_TOPIC)
    public void listenAdminIndexBlogPostCommand(String message) {
        logReceivedMessage(message);
        var adminIndexBlogPostCommand = ContentFarmJsonUtils.deserializeFromJsonString(message, AdminIndexBlogPostCommand.class);
        listenAdminIndexBlogPostCommand(adminIndexBlogPostCommand);
    }

    @Override
    public void listenAdminIndexBlogPostCommand(AdminIndexBlogPostCommand adminIndexBlogPostCommand) {
        BlogPostDomainModel blogPostDomainModel = blogPostMessageQueueMapper.extractBlogPostDomainModel(adminIndexBlogPostCommand);
        blogPostDomainModel = blogPostDomainService.upsertBlogPost(blogPostDomainModel);
        adminIndexBlogPostCommand.setId(blogPostDomainModel.getId());
        blogPostMessageQueueProducer.sendAddBlogPostCompleteMessage(adminIndexBlogPostCommand);
    }

    @Transactional
    @KafkaListener(groupId = ContentFarmKafkaGroupConstant.CONTENT_FARM_KAFKA_GROUP, topics = ContentFarmKafkaTopicConstant.CONTENT_FARM_KAFKA_DELETE_BLOGPOST_TOPIC)
    public void listenAdminDeleteBlogPostCommand(String message) {
        logReceivedMessage(message);
        var adminDeleteBlogPostCommand = ContentFarmJsonUtils.deserializeFromJsonString(message, AdminDeleteBlogPostCommand.class);
        listenAdminDeleteBlogPostCommand(adminDeleteBlogPostCommand);
    }

    @Override
    public void listenAdminDeleteBlogPostCommand(AdminDeleteBlogPostCommand adminDeleteBlogPostCommand) {
        blogPostDomainService.deleteBlogPostById(adminDeleteBlogPostCommand.getId());
    }

    private void logReceivedMessage(String message) {
        logger.info("received message: {}", message);
    }
}
