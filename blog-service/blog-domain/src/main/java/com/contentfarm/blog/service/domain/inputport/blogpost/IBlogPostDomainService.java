package com.contentfarm.blog.service.domain.inputport.blogpost;

import com.contentfarm.blog.service.domain.aggregateroot.blogpost.BlogPostDomainModel;

import java.util.List;

public interface IBlogPostDomainService {
    List<BlogPostDomainModel> findBlogPostByAuthorId(String authorId);
}
