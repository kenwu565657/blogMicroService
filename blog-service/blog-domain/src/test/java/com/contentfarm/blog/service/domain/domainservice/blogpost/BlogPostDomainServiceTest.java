package com.contentfarm.blog.service.domain.domainservice.blogpost;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = BlogTestConfiguration.class)
class BlogPostDomainServiceTest {

    @Autowired
    private BlogPostDomainService blogPostDomainService;

    @Test
    void findBlogPostByAuthorId() {
        var blogPostDomainModel = blogPostDomainService.findBlogPostByAuthorId("");
        assertEquals(1, blogPostDomainModel.size());
    }
}