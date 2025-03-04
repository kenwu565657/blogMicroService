package com.contentfarm.search.controller.blogpost;

import com.contentfarm.dto.blogpost.BlogPostSearchResultDto;
import com.contentfarm.dto.common.SearchResult;
import com.contentfarm.feign.feign.SearchServiceFeign;
import com.contentfarm.search.mapping.blogpost.BlogPostMapper;
import com.contentfarm.search.service.blogpost.IBlogPostSearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("search/blogpost")
@RestController
public class BlogPostSearchController implements SearchServiceFeign {
    @GetMapping(value = "", produces = "application/json", params = {"keyword"})
    @Override
    public SearchResult<BlogPostSearchResultDto> searchBlogPostByKeywordAndPageNumberAndPageSize(@RequestParam("keyword") String keyword,
                                                                                          @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                                                          @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        var searchResult = blogPostSearchService.searchBlogPostByKeywordAndPageNumberAndPageSize(keyword, pageNumber, pageSize);
        return BlogPostMapper.toSearchResult(searchResult);
    }

    @GetMapping(value = "", produces = "application/json", params = {"tagList"})
    @Override
    public SearchResult<BlogPostSearchResultDto> searchBlogPostByTagList(List<String> tagList) {
        if (tagList == null || tagList.isEmpty()) {
            return BlogPostMapper.toSearchResult(blogPostSearchService.searchAllBlogPost());
        }
        var searchResult = blogPostSearchService.searchBlogPostByTagList(tagList);
        return BlogPostMapper.toSearchResult(searchResult);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @Override
    public BlogPostSearchResultDto getBlogPostById(@PathVariable("id") String id) {
        var blogPostDocument = blogPostSearchService.getBlogPostById(id);
        return BlogPostMapper.toBlogPostSearchResultDto(blogPostDocument);
    }

    @GetMapping(value = "")
    @Override
    public SearchResult<BlogPostSearchResultDto> searchAllBlogPost() {
        return BlogPostMapper.toSearchResult(blogPostSearchService.searchAllBlogPost());
    }

    private final IBlogPostSearchService blogPostSearchService;

    public BlogPostSearchController(IBlogPostSearchService blogPostSearchService) {
        this.blogPostSearchService = blogPostSearchService;
    }
}
