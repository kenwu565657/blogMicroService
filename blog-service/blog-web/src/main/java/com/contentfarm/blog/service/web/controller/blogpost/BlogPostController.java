package com.contentfarm.blog.service.web.controller.blogpost;

import com.contentfarm.blog.service.domain.inputport.web.blogpost.IBlogPostWebDomainService;
import com.contentfarm.blog.service.domain.inputport.web.tag.IBlogPostTagWebDomainService;
import com.contentfarm.blog.service.web.mapper.blogpost.BlogPostMapper;
import com.contentfarm.dto.blogpost.BlogPostSummaryDto;
import com.contentfarm.exception.NotFoundByIdException;
import com.contentfarm.feign.feign.BlogPostServiceFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/blogpost")
public class BlogPostController implements BlogPostServiceFeign {
    private final IBlogPostWebDomainService blogPostDomainService;
    private final IBlogPostTagWebDomainService blogPostTagDomainService;
    private final BlogPostMapper blogPostMapper;

    @Override
    @GetMapping(path= "/{blogpostId}", produces = "application/json")
    public BlogPostSummaryDto getBlogPostSummaryById(@PathVariable("blogpostId") String blogpostId) {
        var blogPostDomainModel = blogPostDomainService.getBlogPostById(blogpostId);
        if (null == blogPostDomainModel) {
            throw NotFoundByIdException.of(blogpostId);
        }
        return blogPostMapper.toBlogPostSummaryDto(blogPostDomainModel);
    }

    @Override
    @GetMapping(path = "/list", produces = "application/json")
    public List<BlogPostSummaryDto> findBlogPostSummary() {
        var blogpostDomainModelList = blogPostDomainService.findBlogPostByAuthorId("testingAuthorId1");
        return blogPostMapper.toBlogPostSummaryDtoList(blogpostDomainModelList);
    }

    @Override
    @GetMapping(path = "/content/markdown/{blogpostId}", produces = "text/markdown")
    public byte[] getBlogPostContentAsMarkdownFileByBlogPostId(@PathVariable("blogpostId") String blogpostId) {
        return blogPostDomainService.getBlogPostContentAsMarkdownById(blogpostId);
    }

    @Override
    @GetMapping(path = "/tag/list", produces = "application/json")
    public List<String> findTagList() {
        return blogPostTagDomainService.findTagList();
    }

    @Override
    @PostMapping(path = "/tag", produces = "application/json")
    public void addTagList(@RequestBody List<String> tagList) {
        blogPostTagDomainService.addBlogPostTag(tagList);
    }

    @Override
    @DeleteMapping(path = "/tag", produces = "application/json")
    public void deleteTagList(@RequestBody List<String> tagList) {
        blogPostTagDomainService.deleteBlogPostTag(tagList);
    }
}
