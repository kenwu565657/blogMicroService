package com.contentfarm.persistence.service.blogpost;

import com.contentfarm.contentfarmdomain.aggregateroot.blogpost.BlogPostDomainModel;
import com.contentfarm.contentfarmdomain.outputport.blogpost.IBlogPostPersistenceService;
import com.contentfarm.persistence.dao.blogpost.BlogPostDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BlogPostPersistenceService implements IBlogPostPersistenceService {

    private final BlogPostDao blogPostDao;

    @Override
    public void save(BlogPostDomainModel blogPostDomainModel) {

    }
}
