package com.contentfarm.search.repository.blogpost;

import com.contentfarm.search.document.blogpost.BlogPostDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostElasticsearchRepository extends ElasticsearchRepository<BlogPostDocument, String> {
}
