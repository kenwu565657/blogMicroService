package com.contentfarm.search.service.blogpost.impl;

import com.contentfarm.search.document.blogpost.BlogPostDocument;
import com.contentfarm.search.repository.blogpost.BlogPostElasticsearchRepository;
import com.contentfarm.search.service.blogpost.IBlogPostIndexService;
import org.springframework.stereotype.Service;

@Service
public class BlogPostIndexService implements IBlogPostIndexService {
    private final BlogPostElasticsearchRepository blogPostElasticsearchRepository;

    public BlogPostIndexService(BlogPostElasticsearchRepository blogPostElasticsearchRepository) {
        this.blogPostElasticsearchRepository = blogPostElasticsearchRepository;
    }

    @Override
    public void addDocument(BlogPostDocument blogPostDocument) {
        blogPostElasticsearchRepository.save(blogPostDocument);
    }
}
