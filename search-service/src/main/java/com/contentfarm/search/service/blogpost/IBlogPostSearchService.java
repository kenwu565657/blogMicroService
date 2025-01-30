package com.contentfarm.search.service.blogpost;

import com.contentfarm.search.document.blogpost.BlogPostDocument;
import org.springframework.data.elasticsearch.core.SearchHits;

public interface IBlogPostSearchService {

    SearchHits<BlogPostDocument> searchBlogPostByKeyword(String keyword);
}
