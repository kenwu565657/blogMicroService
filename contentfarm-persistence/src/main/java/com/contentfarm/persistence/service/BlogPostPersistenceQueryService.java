package com.contentfarm.persistence.service;

import com.contentfarm.contentfarmdomain.aggregateroot.blogpost.BlogPostDomainModel;
import com.contentfarm.contentfarmdomain.outputport.blogpost.IBlogPostPersistenceQueryService;
import com.contentfarm.persistence.dao.BlogPostDao;
import com.contentfarm.persistence.entity.BlogPostEntity;
import com.contentfarm.persistence.mapper.BlogPostDomainModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BlogPostPersistenceQueryService implements IBlogPostPersistenceQueryService {

    private final BlogPostDao blogPostDao;
    private final BlogPostDomainModelMapper blogPostDomainModelMapper;

    @Override
    public BlogPostDomainModel getById(String id) {
        BlogPostEntity blogPostEntity = blogPostDao.getById(id);
        return blogPostDomainModelMapper.mapToBlogPostDomainModel(blogPostEntity);
    }

}
