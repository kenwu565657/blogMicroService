package com.contentfarm.blog.service.web.controller.blogpost;

import com.contentfarm.blog.service.domain.aggregateroot.blogpost.BlogPostDomainModel;
import com.contentfarm.blog.service.domain.inputport.blogpost.IBlogPostDomainService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/blogpost", produces = "application/vnd.api.v1+json")
public class BlogPostController {
    private final IBlogPostDomainService blogPostDomainService;

    @GetMapping
    public List<BlogPostDomainModel> getBlogPost() {
        return blogPostDomainService.findBlogPostByAuthorId("testing_id");
    }

}
