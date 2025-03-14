package com.contentfarm.search.service.blogpost;

import com.contentfarm.search.document.blogpost.BlogPostDocument;

public interface IBlogPostIndexService {
    void addDocument(BlogPostDocument blogPostDocument);
    void deleteDocument(String id);
}
