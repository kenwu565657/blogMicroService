package com.contentfarm.contentfarmdomain.outputport.blogpost;

import com.contentfarm.contentfarmdomain.aggregateroot.blogpost.BlogPostDomainModel;

public interface IBlogPostPersistenceQueryService {
    BlogPostDomainModel getById(String id);
    Integer countByAuthorId(String authorId);
}
