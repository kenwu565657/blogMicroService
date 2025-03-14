package com.contentfarm.blog.service.message.queue.mapper;

import com.contentfarm.blog.service.domain.aggregateroot.blogpost.BlogPostDomainModel;
import com.contentfarm.command.blogpost.AdminIndexBlogPostCommand;
import com.contentfarm.utils.ContentFarmLocaleDateTimeUtils;
import org.springframework.stereotype.Component;

@Component
public class BlogPostMessageQueueMapper {
    public BlogPostDomainModel extractBlogPostDomainModel(AdminIndexBlogPostCommand adminIndexBlogPostCommand) {
        BlogPostDomainModel blogPostDomainModel = new BlogPostDomainModel();
        blogPostDomainModel.setId(adminIndexBlogPostCommand.getId());
        blogPostDomainModel.setTitle(adminIndexBlogPostCommand.getTitle());
        blogPostDomainModel.setTagList(adminIndexBlogPostCommand.getTagList());
        blogPostDomainModel.setSummary(adminIndexBlogPostCommand.getSummary());
        blogPostDomainModel.setAuthorId(adminIndexBlogPostCommand.getAuthorName());
        blogPostDomainModel.setCreatedDateTime(null == adminIndexBlogPostCommand.getPostDate() ? ContentFarmLocaleDateTimeUtils.ofNow() : adminIndexBlogPostCommand.getPostDate());
        blogPostDomainModel.setCoverImageUrl(adminIndexBlogPostCommand.getCoverImageUrl());
        blogPostDomainModel.setContentType(adminIndexBlogPostCommand.getContentType());
        blogPostDomainModel.setContentFileName(adminIndexBlogPostCommand.getContentFileName());
        return blogPostDomainModel;
    }
}
