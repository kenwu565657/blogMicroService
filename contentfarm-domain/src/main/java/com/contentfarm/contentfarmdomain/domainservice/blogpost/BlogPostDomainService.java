package com.contentfarm.contentfarmdomain.domainservice.blogpost;

import com.contentfarm.contentfarmdomain.aggregateroot.blogpost.BlogPostDomainModel;
import com.contentfarm.contentfarmdomain.inputport.blogpost.IBlogPostDomainService;
import com.contentfarm.contentfarmdomain.outputport.blogpost.IBlogPostPersistenceQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
//@Service
public class BlogPostDomainService implements IBlogPostDomainService {

    private final IBlogPostPersistenceQueryService blogPostPersistenceQueryService;


    public BlogPostDomainModel getBlogPostById(String id) {
        return null;
    }

    public void createBlogPost(BlogPostDomainModel blogPostDomainModel) {

    }

    @Override
    public List<BlogPostDomainModel> findBlogPostByAuthorId(String authorId) {
        return blogPostPersistenceQueryService.findByAuthorId(authorId);
    }

}
