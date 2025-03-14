package com.contentfarm.blog.service.domain.inputport.web.blogpost;

import com.contentfarm.blog.service.domain.aggregateroot.blogpost.BlogPostDomainModel;

import java.util.List;

public interface IBlogPostWebDomainService {
    List<BlogPostDomainModel> findBlogPostByAuthorId(String authorId);

    String getBlogPostContentByKey(String key);

    byte[] getBlogPostContentAsMarkdownByKey(String key);

    byte[] getBlogPostContentAsMarkdownById(String Id);
}
