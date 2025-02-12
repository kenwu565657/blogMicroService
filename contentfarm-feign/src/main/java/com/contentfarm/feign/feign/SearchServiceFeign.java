package com.contentfarm.feign.feign;

import com.contentfarm.dto.blogpost.BlogPostSearchResultDto;
import com.contentfarm.dto.common.SearchResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(contextId = "search-service", name = "search-service", path = "/search")
public interface SearchServiceFeign {
    @GetMapping(value = "/blogpost/{keyword}", produces = "application/json")
    SearchResult<BlogPostSearchResultDto> searchBlogPostByKeyword(@PathVariable("keyword") String keyword);
}
