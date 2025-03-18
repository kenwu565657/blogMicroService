package com.contentfarm.blog.service.domain.outputport.persistence;

import com.contentfarm.blog.service.domain.aggregateroot.blogpost.BlogPostDomainModel;

import java.util.List;

public interface IBlogPostPersistenceQueryService {
    BlogPostDomainModel getById(String id);
    List<BlogPostDomainModel> findByAuthorId(String authorId);
    byte[] getBlogPostContentByKey(String key);
    List<String> findTagList();
    byte[] getBlogPostContentById(String id);
}
