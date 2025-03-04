package com.contentfarm.search.mapping.blogpost;

import com.contentfarm.search.document.blogpost.BlogPostDocument;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchAggregations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.SearchHitsImpl;
import org.springframework.data.elasticsearch.core.SearchShardStatistics;
import org.springframework.data.elasticsearch.core.TotalHitsRelation;
import org.springframework.data.elasticsearch.core.suggest.response.Suggest;

import java.text.MessageFormat;
import java.time.Duration;
import java.util.List;
import java.util.Map;

class BlogPostMapperTest {

    @Nested
    class TestToSearchResult {
        @Test
        void testToSearchResult_haveResultCase() {
            var testingSearchHit = findTestingSearchHit();

            SearchHits<BlogPostDocument> searchHits = new SearchHitsImpl<>(
                    3L,
                    TotalHitsRelation.EQUAL_TO,
            0.87f,
            Duration.ofMillis(3000),
            "scrollId",
            "pointInTimeId",
            testingSearchHit,
            new ElasticsearchAggregations(Map.of()),
            new Suggest(List.of(), false),
            SearchShardStatistics.of(0, 1, 1, 0, List.of())
            );

            var result = BlogPostMapper.toSearchResult(searchHits);
            Assertions.assertNotNull(result);
            Assertions.assertEquals(3, result.getSearchResultCount());
            Assertions.assertEquals(0.87f, result.getMaxSearchScore());
            Assertions.assertEquals(3000, result.getSearchExecutionTimeInMs());
            Assertions.assertFalse(result.getSearchResultItemList().isEmpty());
            Assertions.assertEquals(3, result.getSearchResultItemList().size());

            Assertions.assertEquals("testingId1", result.getSearchResultItemList().getFirst().getId());
            Assertions.assertEquals("testingTitle1", result.getSearchResultItemList().getFirst().getTitle());
            Assertions.assertEquals(List.of("testingTag1", "testingTag2"), result.getSearchResultItemList().getFirst().getTagList());
            Assertions.assertEquals("This is a testingSummary 1", result.getSearchResultItemList().getFirst().getSummary());
            Assertions.assertEquals("testingAuthorName1", result.getSearchResultItemList().getFirst().getAuthorName());
            Assertions.assertEquals("testingPostDate1", result.getSearchResultItemList().getFirst().getPostDate());
            Assertions.assertEquals("testingImageUrl1", result.getSearchResultItemList().getFirst().getImageUrl());

            Assertions.assertEquals("testingId2", result.getSearchResultItemList().get(1).getId());
            Assertions.assertEquals("testingTitle2", result.getSearchResultItemList().get(1).getTitle());
            Assertions.assertEquals(List.of("testingTag1", "testingTag2"), result.getSearchResultItemList().get(1).getTagList());
            Assertions.assertEquals("This is a testingSummary 2", result.getSearchResultItemList().get(1).getSummary());
            Assertions.assertEquals("testingAuthorName2", result.getSearchResultItemList().get(1).getAuthorName());
            Assertions.assertEquals("testingPostDate2", result.getSearchResultItemList().get(1).getPostDate());
            Assertions.assertEquals("testingImageUrl2", result.getSearchResultItemList().get(1).getImageUrl());

            Assertions.assertEquals("testingId3", result.getSearchResultItemList().get(2).getId());
            Assertions.assertEquals("testingTitle3", result.getSearchResultItemList().get(2).getTitle());
            Assertions.assertEquals(List.of("testingTag1", "testingTag2"), result.getSearchResultItemList().get(2).getTagList());
            Assertions.assertEquals("This is a testingSummary 3", result.getSearchResultItemList().get(2).getSummary());
            Assertions.assertEquals("testingAuthorName3", result.getSearchResultItemList().get(2).getAuthorName());
            Assertions.assertEquals("testingPostDate3", result.getSearchResultItemList().get(2).getPostDate());
            Assertions.assertEquals("testingImageUrl3", result.getSearchResultItemList().get(2).getImageUrl());
        }

        @Test
        void testToSearchResult_noResultCase() {
            SearchHits<BlogPostDocument> searchHits = new SearchHitsImpl<>(
                    0L,
                    TotalHitsRelation.EQUAL_TO,
                    0f,
                    Duration.ofMillis(3000),
                    "scrollId",
                    "pointInTimeId",
                    List.of(),
                    new ElasticsearchAggregations(Map.of()),
                    new Suggest(List.of(), false),
                    SearchShardStatistics.of(0, 1, 1, 0, List.of())
            );

            var result = BlogPostMapper.toSearchResult(searchHits);
            Assertions.assertNotNull(result);
            Assertions.assertEquals(0, result.getSearchResultCount());
            Assertions.assertEquals(0, result.getMaxSearchScore());
            Assertions.assertEquals(3000, result.getSearchExecutionTimeInMs());
            Assertions.assertTrue(result.getSearchResultItemList().isEmpty());
        }
    }

    @Test
    void toBlogPostSearchResultDto() {
        BlogPostDocument testingBlogPostDocument = BlogPostDocument.builder()
                .id("testingId1")
                .title("testingTitle1")
                .tagList(List.of("testingTag1", "testingTag2"))
                .summary("testingSummary1")
                .authorName("testingAuthorName1")
                .postDate("testingPostDate1")
                .imageUrl("testingImageUrl1")
                .build();
        var result = BlogPostMapper.toBlogPostSearchResultDto(testingBlogPostDocument);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(testingBlogPostDocument.getId(), result.getId());
        Assertions.assertEquals(testingBlogPostDocument.getTitle(), result.getTitle());
        Assertions.assertEquals(testingBlogPostDocument.getTagList(), result.getTagList());
        Assertions.assertEquals(testingBlogPostDocument.getSummary(), result.getSummary());
        Assertions.assertEquals(testingBlogPostDocument.getAuthorName(), result.getAuthorName());
        Assertions.assertEquals(testingBlogPostDocument.getPostDate(), result.getPostDate());
        Assertions.assertEquals(testingBlogPostDocument.getImageUrl(), result.getImageUrl());
    }

    @Test
    void toBlogPostSearchResultDto_nullTagList() {
        BlogPostDocument testingBlogPostDocument = BlogPostDocument.builder()
                .id("testingId1")
                .title("testingTitle1")
                .tagList(null)
                .summary("testingSummary1")
                .authorName("testingAuthorName1")
                .postDate("testingPostDate1")
                .imageUrl("testingImageUrl1")
                .build();
        var result = BlogPostMapper.toBlogPostSearchResultDto(testingBlogPostDocument);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(testingBlogPostDocument.getId(), result.getId());
        Assertions.assertEquals(testingBlogPostDocument.getTitle(), result.getTitle());
        Assertions.assertEquals(List.of(), result.getTagList());
        Assertions.assertEquals(testingBlogPostDocument.getSummary(), result.getSummary());
        Assertions.assertEquals(testingBlogPostDocument.getAuthorName(), result.getAuthorName());
        Assertions.assertEquals(testingBlogPostDocument.getPostDate(), result.getPostDate());
        Assertions.assertEquals(testingBlogPostDocument.getImageUrl(), result.getImageUrl());
    }

    @Test
    void toBlogPostSearchResultDto_null() {
        var result = BlogPostMapper.toBlogPostSearchResultDto(null);
        Assertions.assertNull(result);
    }

    private List<SearchHit<BlogPostDocument>> findTestingSearchHit() {
        return List.of(
                buildTestingSearchHit(0.5f, 1),
                buildTestingSearchHit(0.77f, 2),
                buildTestingSearchHit(0.87f, 3)
        );
    }

    private SearchHit<BlogPostDocument> buildTestingSearchHit(float score, int orderNumber) {
        BlogPostDocument testingBlogPostDocument = BlogPostDocument.builder()
                .id(MessageFormat.format("testingId{0}", orderNumber))
                .title(MessageFormat.format("testingTitle{0}", orderNumber))
                .tagList(List.of("testingTag1", "testingTag2"))
                .summary(MessageFormat.format("This is a testingSummary {0}", orderNumber))
                .authorName(MessageFormat.format("testingAuthorName{0}", orderNumber))
                .postDate(MessageFormat.format("testingPostDate{0}", orderNumber))
                .imageUrl(MessageFormat.format("testingImageUrl{0}", orderNumber))
                .build();

        return new SearchHit<>(
                null,
                null,
                null,
                score,
                null,
                null,
                null,
                null,
                null,
                null,
                testingBlogPostDocument
        );
    }
}
