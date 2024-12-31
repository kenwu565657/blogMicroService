package com.contentfarm.contentfarmdomain.inputport.blogpost;

import com.contentfarm.contentfarmdomain.aggregateroot.blogpost.BlogPostDomainModel;

public interface IBlogPostDomainService {
    BlogPostDomainModel getBlogPostById(String id);
    void createBlogPost(BlogPostDomainModel blogPostDomainModel);
}
