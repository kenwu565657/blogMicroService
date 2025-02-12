package com.contentfarm.search.service.blogpost.impl;

import com.contentfarm.search.service.blogpost.IBlogPostIndexService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BlogPostIndexServiceTest {

    @Autowired
    IBlogPostIndexService blogPostIndexService;

    @Test
    void addDocument() {
    }
}