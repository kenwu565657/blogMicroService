package com.contentfarm.search.mapping.blogpost;

import com.contentfarm.dto.blogpost.BlogPostSearchResultDto;
import com.contentfarm.dto.common.SearchResult;
import com.contentfarm.search.document.blogpost.BlogPostDocument;
import lombok.experimental.UtilityClass;
import org.springframework.data.elasticsearch.core.SearchHits;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class BlogPostMapper {

    public static SearchResult<BlogPostSearchResultDto> toSearchResult(SearchHits<BlogPostDocument> blogPostSearchHits) {
        var searchResult = new SearchResult<BlogPostSearchResultDto>();

        var blogPostSearchResultDtoList = toBlogPostSearchResultDto(blogPostSearchHits);
        searchResult.setSearchResultItemList(blogPostSearchResultDtoList);

        searchResult.setSearchResultCount((int) blogPostSearchHits.getTotalHits());
        searchResult.setSearchExecutionTimeInMs(blogPostSearchHits.getExecutionDuration().toMillis());
        searchResult.setMaxSearchScore(blogPostSearchHits.getMaxScore());

        return searchResult;
    }

    public static BlogPostSearchResultDto toBlogPostSearchResultDto(BlogPostDocument blogPostDocument) {
        var blogPostSearchResultDto = new BlogPostSearchResultDto();
        if (null == blogPostDocument) {
            return null;
        }
        blogPostSearchResultDto.setId(blogPostDocument.getId());
        blogPostSearchResultDto.setTitle(blogPostDocument.getTitle());
        blogPostSearchResultDto.setTagList(null == blogPostDocument.getTagList() ? List.of() : blogPostDocument.getTagList());
        blogPostSearchResultDto.setSummary(blogPostDocument.getSummary());
        blogPostSearchResultDto.setAuthorName(blogPostDocument.getAuthorName());
        blogPostSearchResultDto.setPostDate(blogPostDocument.getPostDate());
        blogPostSearchResultDto.setImageUrl(blogPostDocument.getImageUrl());
        return blogPostSearchResultDto;
    }

    private static List<BlogPostSearchResultDto> toBlogPostSearchResultDto(SearchHits<BlogPostDocument> searchHits) {
        List<BlogPostSearchResultDto> blogPostSearchResultDtoList = new ArrayList<>();
        if (searchHits.getTotalHits() > 0) {
            blogPostSearchResultDtoList = searchHits.getSearchHits().stream().map(x -> toBlogPostSearchResultDto(x.getContent())).toList();
        }
        return blogPostSearchResultDtoList;
    }
}
