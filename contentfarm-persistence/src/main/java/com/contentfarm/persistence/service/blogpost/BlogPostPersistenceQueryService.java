package com.contentfarm.persistence.service.blogpost;

import com.contentfarm.contentfarmdomain.aggregateroot.blogpost.BlogPostDomainModel;
import com.contentfarm.contentfarmdomain.outputport.blogpost.IBlogPostPersistenceQueryService;
import com.contentfarm.persistence.dao.blogpost.BlogPostDao;
import com.contentfarm.persistence.entity.blogpost.BlogPostEntity;
import com.contentfarm.persistence.mapper.blogpost.BlogPostDomainModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BlogPostPersistenceQueryService implements IBlogPostPersistenceQueryService {

    private final BlogPostDao blogPostDao;
    private final BlogPostDomainModelMapper blogPostDomainModelMapper;

    @Override
    public BlogPostDomainModel getById(String id) {
        return null;
        //BlogPostEntity blogPostEntity = blogPostDao.getById(id);
        //return blogPostDomainModelMapper.mapToBlogPostDomainModel(blogPostEntity);
    }

    @Override
    public Integer countByAuthorId(String authorId) {
        return null;
    }

    @Override
    public List<BlogPostDomainModel> findByAuthorId(String authorId) {
        List<BlogPostEntity> blogPostEntities = blogPostDao.findByAuthorId(authorId);
        return blogPostDomainModelMapper.mapToBlogPostDomainModels(blogPostEntities);
    }


}
