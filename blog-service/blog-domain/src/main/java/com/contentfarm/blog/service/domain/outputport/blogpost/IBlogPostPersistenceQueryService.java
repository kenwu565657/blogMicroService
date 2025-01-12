package com.contentfarm.blog.service.domain.outputport.blogpost;

import com.contentfarm.blog.service.domain.aggregateroot.blogpost.BlogPostDomainModel;

import java.util.List;

public interface IBlogPostPersistenceQueryService {
    List<BlogPostDomainModel> findByAuthorId(String authorId);
}
