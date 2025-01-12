package com.contentfarm.contentfarmdomain.outputport.blogpost;

import com.contentfarm.contentfarmdomain.aggregateroot.blogpost.BlogPostDomainModel;

import java.util.List;

public interface IBlogPostPersistenceQueryService {
    BlogPostDomainModel getById(String id);
    Integer countByAuthorId(String authorId);
    List<BlogPostDomainModel> findByAuthorId(String authorId);
}
