package com.contentfarm.contentfarmdomain.outputport.blogpost;

import com.contentfarm.contentfarmdomain.aggregateroot.blogpost.BlogPostDomainModel;

public interface IBlogPostPersistenceService {
    void save(BlogPostDomainModel blogPostDomainModel);
}
