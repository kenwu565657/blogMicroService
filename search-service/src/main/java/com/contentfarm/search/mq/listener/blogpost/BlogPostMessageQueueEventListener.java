package com.contentfarm.search.mq.listener.blogpost;

import com.contentfarm.command.blogpost.AdminDeleteBlogPostCommand;
import com.contentfarm.command.blogpost.AdminIndexBlogPostCommand;
import com.contentfarm.kafka.springboot.starter.constant.ContentFarmKafkaGroupConstant;
import com.contentfarm.kafka.springboot.starter.constant.ContentFarmKafkaTopicConstant;
import com.contentfarm.search.document.blogpost.BlogPostDocument;
import com.contentfarm.search.mq.publisher.BlogPostMessageQueueEventPublisher;
import com.contentfarm.search.service.blogpost.IBlogPostIndexService;
import com.contentfarm.utils.ContentFarmJsonUtils;
import com.contentfarm.utils.ContentFarmLocaleDateTimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class BlogPostMessageQueueEventListener {
    private final IBlogPostIndexService blogPostIndexService;
    private final BlogPostMessageQueueEventPublisher blogPostMessageQueueEventPublisher;

    @Transactional
    @KafkaListener(
            groupId = ContentFarmKafkaGroupConstant.CONTENT_FARM_KAFKA_GROUP,
            topics = ContentFarmKafkaTopicConstant.CONTENT_FARM_KAFKA_INDEX_BLOGPOST_TOPIC
    )
    public void listenIndexBlogPostMessage(String message) {
        AdminIndexBlogPostCommand adminIndexBlogPostCommand = ContentFarmJsonUtils.deserializeFromJsonString(message, AdminIndexBlogPostCommand.class);
        var blogpostDocument = extractBlogPostDocumentFromAdminIndexBlogPostCommand(adminIndexBlogPostCommand);
        blogPostIndexService.addDocument(blogpostDocument);
    }

    @Transactional
    @KafkaListener(
            groupId = ContentFarmKafkaGroupConstant.CONTENT_FARM_KAFKA_GROUP,
            topics = ContentFarmKafkaTopicConstant.CONTENT_FARM_KAFKA_DE_INDEX_BLOGPOST_TOPIC
    )
    public void listenDeIndexBlogPostMessage(String message) {
        AdminDeleteBlogPostCommand adminIndexBlogPostCommand = ContentFarmJsonUtils.deserializeFromJsonString(message, AdminDeleteBlogPostCommand.class);
        blogPostIndexService.deleteDocument(adminIndexBlogPostCommand.getId());
        blogPostMessageQueueEventPublisher.sendBlogPostDeIndexFinishEvent(message);
    }

    private BlogPostDocument extractBlogPostDocumentFromAdminIndexBlogPostCommand(AdminIndexBlogPostCommand adminIndexBlogPostCommand) {
        return BlogPostDocument
                .builder()
                .id(adminIndexBlogPostCommand.getId())
                .title(adminIndexBlogPostCommand.getTitle())
                .tagList(adminIndexBlogPostCommand.getTagList())
                .summary(adminIndexBlogPostCommand.getSummary())
                .authorName(adminIndexBlogPostCommand.getAuthorName())
                .postDate(ContentFarmLocaleDateTimeUtils.formatTo_yyyy_MM_dd(adminIndexBlogPostCommand.getPostDate()))
                .imageUrl(adminIndexBlogPostCommand.getCoverImageUrl())
                .build();
    }
}
