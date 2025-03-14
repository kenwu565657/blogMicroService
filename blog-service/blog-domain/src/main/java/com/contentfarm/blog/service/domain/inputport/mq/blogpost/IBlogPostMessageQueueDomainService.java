package com.contentfarm.blog.service.domain.inputport.mq.blogpost;

import com.contentfarm.blog.service.domain.aggregateroot.blogpost.BlogPostDomainModel;

public interface IBlogPostMessageQueueDomainService {
    BlogPostDomainModel upsertBlogPost(BlogPostDomainModel blogPostDomainModel);
    String deleteBlogPostById(String Id);
}
