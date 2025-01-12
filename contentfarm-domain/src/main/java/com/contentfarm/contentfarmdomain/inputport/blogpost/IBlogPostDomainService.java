package com.contentfarm.contentfarmdomain.inputport.blogpost;

import com.contentfarm.contentfarmdomain.aggregateroot.blogpost.BlogPostDomainModel;

import java.util.List;

public interface IBlogPostDomainService {
    BlogPostDomainModel getBlogPostById(String id);
    void createBlogPost(BlogPostDomainModel blogPostDomainModel);
    List<BlogPostDomainModel> findBlogPostByAuthorId(String authorId);
}
