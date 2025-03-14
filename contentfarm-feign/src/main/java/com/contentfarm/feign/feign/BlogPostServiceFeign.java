package com.contentfarm.feign.feign;

import com.contentfarm.dto.blogpost.BlogPostSummaryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(contextId = "blogpost-service", name = "blogpost-service", path = "/blogpost")
public interface BlogPostServiceFeign {
    @GetMapping(path = "/list", produces = "application/json")
    List<BlogPostSummaryDto> findBlogPostSummary();

    @GetMapping(path = "/content/{blogpostId}", produces = "text/html")
    String getBlogPostContentByBlogPostId(@PathVariable("blogpostId") String blogpostId);

    @GetMapping(path = "/content/markdown/{blogpostId}", produces = "text/markdown")
    byte[] getBlogPostContentAsMarkdownFileByBlogPostId(@PathVariable("blogpostId") String blogpostId);

    @GetMapping(path = "/tag/list", produces = "application/json")
    List<String> findTagList();

    @PostMapping(path = "/tag", produces = "application/json")
    void addTagList(@RequestBody List<String> tagList);

    @DeleteMapping(path = "/tag", produces = "application/json")
    void deleteTagList(@RequestBody List<String> tagList);
}
