package com.contentfarm.service.controller;

import com.contentfarm.contentfarmdomain.aggregateroot.blogpost.BlogPostDomainModel;
import com.contentfarm.contentfarmdomain.inputport.blogpost.IBlogPostDomainService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class BlogPostController {
    private final IBlogPostDomainService blogPostDomainService;

    @GetMapping("/")
    public List<BlogPostDomainModel> getBlogPost() {
        return blogPostDomainService.findBlogPostByAuthorId("");
    }

}
