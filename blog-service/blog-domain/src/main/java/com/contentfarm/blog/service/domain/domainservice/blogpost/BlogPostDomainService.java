package com.contentfarm.blog.service.domain.domainservice.blogpost;

import com.contentfarm.blog.service.domain.aggregateroot.blogpost.BlogPostDomainModel;
import com.contentfarm.blog.service.domain.inputport.blogpost.IBlogPostDomainService;
import com.contentfarm.blog.service.domain.outputport.blogpost.IBlogPostPersistenceQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BlogPostDomainService implements IBlogPostDomainService {
    private final IBlogPostPersistenceQueryService blogPostPersistenceQueryService;

    @Override
    public List<BlogPostDomainModel> findBlogPostByAuthorId(String authorId) {
        return blogPostPersistenceQueryService.findByAuthorId(authorId);
    }
}
