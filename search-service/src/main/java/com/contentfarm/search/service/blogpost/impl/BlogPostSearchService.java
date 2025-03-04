package com.contentfarm.search.service.blogpost.impl;

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MultiMatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.TermQuery;
import com.contentfarm.search.document.blogpost.BlogPostDocument;
import com.contentfarm.search.exception.DocumentNotFoundException;
import com.contentfarm.search.service.blogpost.IBlogPostSearchService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.SearchOperations;
import org.springframework.data.elasticsearch.core.query.HighlightQuery;
import org.springframework.data.elasticsearch.core.query.Order;
import org.springframework.data.elasticsearch.core.query.highlight.Highlight;
import org.springframework.data.elasticsearch.core.query.highlight.HighlightField;
import org.springframework.data.elasticsearch.core.query.highlight.HighlightFieldParameters;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogPostSearchService implements IBlogPostSearchService {
    private final SearchOperations searchOperations;
    private final ElasticsearchOperations elasticsearchOperations;
    private final Pageable defaultPageable = PageRequest.of(0, 20);

    public BlogPostSearchService(SearchOperations searchOperations, ElasticsearchOperations elasticsearchOperations) {
        this.searchOperations = searchOperations;
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @Override
    public SearchHits<BlogPostDocument> searchAllBlogPost() {
        NativeQuery nativeQuery = new NativeQueryBuilder()
                .withPageable(defaultPageable)
                .build();
        return searchOperations.search(nativeQuery, BlogPostDocument.class);
    }

    @Override
    public BlogPostDocument getBlogPostById(String id) {
        var blogPostDocument = elasticsearchOperations.get(id, BlogPostDocument.class);
        if (null == blogPostDocument) {
            throw DocumentNotFoundException.of();
        }
        return blogPostDocument;
    }

    @Override
    public SearchHits<BlogPostDocument> searchBlogPostByKeywordAndPageNumberAndPageSize(String keyword, Integer pageNumber, Integer pageSize) {
        int exactPageNumber = null == pageNumber ? 0 : Math.max(0, pageNumber);
        int exactPageSize = null == pageSize ? 20 : Math.min(20, pageSize);
        Sort sort = Sort.by(new Order(Sort.Direction.DESC, "_score"));
        Pageable pageable = PageRequest.of(exactPageNumber, exactPageSize, sort);

        final HighlightFieldParameters.HighlightFieldParametersBuilder highlightFieldParametersBuilder = HighlightFieldParameters.builder();
        highlightFieldParametersBuilder.withPreTags("<font color='red'>")
                .withPostTags("</font>")
                .withRequireFieldMatch(true)
                .withNumberOfFragments(0);
        final HighlightField titleHighlightField = new HighlightField(BlogPostDocument.Fields.title, highlightFieldParametersBuilder.build());
        final HighlightField contentHighlightField = new HighlightField(BlogPostDocument.Fields.summary, highlightFieldParametersBuilder.build());
        final Highlight highLightField = new Highlight(List.of(titleHighlightField,contentHighlightField));

        MultiMatchQuery.Builder multiMatchQueryBuilder = new MultiMatchQuery.Builder();
        multiMatchQueryBuilder.query(keyword);
        multiMatchQueryBuilder.fields(List.of(BlogPostDocument.Fields.title, BlogPostDocument.Fields.summary));

        var nativeQuery = new NativeQueryBuilder()
                .withQuery(q -> q.multiMatch(multiMatchQueryBuilder.build()))
                .withPageable(pageable)
                .withHighlightQuery(new HighlightQuery(highLightField, BlogPostDocument.class))
                .build();
        return elasticsearchOperations.search(nativeQuery, BlogPostDocument.class);
    }

    @Override
    public SearchHits<BlogPostDocument> searchBlogPostByTagList(List<String> tagList) {
        BoolQuery.Builder boolQueryBuilder = new BoolQuery.Builder();
        boolQueryBuilder.minimumShouldMatch(String.valueOf(tagList.size()));
        List<Query> tagQueries = tagList.stream().map(p -> {
            TermQuery termQuery = new TermQuery.Builder().field(BlogPostDocument.Fields.tagList).value(p).build();
            return Query.of(q -> q.term(termQuery));
        }).toList();
        boolQueryBuilder.should(tagQueries);

        Sort sort = Sort.by(new Order(Sort.Direction.DESC, "_score"));
        Pageable pageable = PageRequest.of(0, 20, sort);

        var nativeQuery = new NativeQueryBuilder()
                .withQuery(q -> q.bool(boolQueryBuilder.build()))
                .withPageable(pageable)
                .build();
        return elasticsearchOperations.search(nativeQuery, BlogPostDocument.class);
    }
}
