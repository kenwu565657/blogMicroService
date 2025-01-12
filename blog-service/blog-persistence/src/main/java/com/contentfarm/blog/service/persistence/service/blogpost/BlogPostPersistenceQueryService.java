package com.contentfarm.blog.service.persistence.service.blogpost;

import com.contentfarm.blog.service.domain.aggregateroot.blogpost.BlogPostDomainModel;
import com.contentfarm.blog.service.domain.outputport.blogpost.IBlogPostPersistenceQueryService;
import com.contentfarm.blog.service.persistence.dao.blogpost.BlogPostDao;
import com.contentfarm.blog.service.persistence.mapper.blogpost.BlogPostDomainModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BlogPostPersistenceQueryService implements IBlogPostPersistenceQueryService {

    private final BlogPostDao blogPostDao;
    private final BlogPostDomainModelMapper blogPostDomainModelMapper;

    @Override
    public List<BlogPostDomainModel> findByAuthorId(String authorId) {
        var blogPostEntityList = blogPostDao.findByAuthorId(authorId);
        return blogPostDomainModelMapper.mapToBlogPostDomainModels(blogPostEntityList);
    }
}
