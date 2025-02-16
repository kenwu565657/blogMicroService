package com.contentfarm.feign.feign;

import com.contentfarm.dto.blogpost.BlogPostSearchResultDto;
import com.contentfarm.dto.common.SearchResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(contextId = "search-service", name = "search-service", path = "/search")
public interface SearchServiceFeign {
    @GetMapping(value = "/blogpost", produces = "application/json", params = {"keyword"})
    SearchResult<BlogPostSearchResultDto> searchBlogPostByKeywordAndPageNumberAndPageSize(@RequestParam("keyword") String keyword,
                                                                                          @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                                                          @RequestParam(value = "pageSize", required = false) Integer pageSize);

    @GetMapping(value = "/blogpost", produces = "application/json", params = "tagList")
    SearchResult<BlogPostSearchResultDto> searchBlogPostByTagList(@RequestParam("tagList") List<String> tagList);

    @GetMapping(value = "/blogpost", produces = "application/json")
    SearchResult<BlogPostSearchResultDto> searchAllBlogPost();
}
