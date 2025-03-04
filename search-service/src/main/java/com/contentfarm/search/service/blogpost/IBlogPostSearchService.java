package com.contentfarm.search.service.blogpost;

import com.contentfarm.search.document.blogpost.BlogPostDocument;
import org.springframework.data.elasticsearch.core.SearchHits;

import java.util.List;

public interface IBlogPostSearchService {
    SearchHits<BlogPostDocument> searchAllBlogPost();

    BlogPostDocument getBlogPostById(String id);

    SearchHits<BlogPostDocument> searchBlogPostByKeywordAndPageNumberAndPageSize(String keyword, Integer pageNumber, Integer pageSize);

    SearchHits<BlogPostDocument> searchBlogPostByTagList(List<String> tagList);
}
