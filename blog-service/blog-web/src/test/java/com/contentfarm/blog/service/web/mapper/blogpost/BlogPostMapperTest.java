package com.contentfarm.blog.service.web.mapper.blogpost;

import com.contentfarm.blog.service.domain.aggregateroot.blogpost.BlogPostDomainModel;
import com.contentfarm.constant.blogpost.BlogPostContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BlogPostMapperTest {
    private BlogPostMapper blogPostMapper = new BlogPostMapper();

    @Test
    void toBlogPostSummaryDtoList_emptyList() {
        var summaryList = blogPostMapper.toBlogPostSummaryDtoList(List.of());
        Assertions.assertNotNull(summaryList);
        Assertions.assertEquals(0, summaryList.size());
    }

    @Test
    void toBlogPostSummaryDtoList_nullList() {
        var summaryList = blogPostMapper.toBlogPostSummaryDtoList(null);
        Assertions.assertNotNull(summaryList);
        Assertions.assertEquals(0, summaryList.size());
    }

    @Test
    void toBlogPostSummaryDtoList_haveNullElementList() {
        var model = BlogPostDomainModel.builder()
                .id("testingId1")
                .title("testingTitle1")
                .summary("testingSummary1")
                .content("testingContent1")
                .authorId("testingAuthorId1")
                .tagList(List.of("Java", "SpringBoot", "Unit testing"))
                .coverImageUrl("testing.jpg")
                .contentType(BlogPostContentType.MARKDOWN)
                .contentFileName("testing1.md")
                .createdDateTime(LocalDateTime.of(2025, 3, 18, 0, 0, 0))
                .build();
        var model2 = BlogPostDomainModel.builder()
                .id("testingId1")
                .title("testingTitle1")
                .summary("testingSummary1")
                .content("testingContent1")
                .authorId("testingAuthorId1")
                .tagList(List.of("Java", "SpringBoot", "Unit testing"))
                .coverImageUrl("testing.jpg")
                .contentType(BlogPostContentType.MARKDOWN)
                .contentFileName("testing1.md")
                .createdDateTime(LocalDateTime.of(2025, 3, 18, 0, 0, 0))
                .build();
        var myList = new ArrayList<BlogPostDomainModel>();
        myList.add(model);
        myList.add(model2);
        myList.add(null);
        var summaryList = blogPostMapper.toBlogPostSummaryDtoList(myList);
        Assertions.assertNotNull(summaryList);
        Assertions.assertEquals(2, summaryList.size());
    }

    @Test
    void toBlogPostSummaryDtoList() {
        var model = BlogPostDomainModel.builder()
                .id("testingId1")
                .title("testingTitle1")
                .summary("testingSummary1")
                .content("testingContent1")
                .authorId("testingAuthorId1")
                .tagList(List.of("Java", "SpringBoot", "Unit testing"))
                .coverImageUrl("testing.jpg")
                .contentType(BlogPostContentType.MARKDOWN)
                .contentFileName("testing1.md")
                .createdDateTime(LocalDateTime.of(2025, 3, 18, 0, 0, 0))
                .build();
        var model2 = BlogPostDomainModel.builder()
                .id("testingId1")
                .title("testingTitle1")
                .summary("testingSummary1")
                .content("testingContent1")
                .authorId("testingAuthorId1")
                .tagList(List.of("Java", "SpringBoot", "Unit testing"))
                .coverImageUrl("testing.jpg")
                .contentType(BlogPostContentType.MARKDOWN)
                .contentFileName("testing1.md")
                .createdDateTime(LocalDateTime.of(2025, 3, 18, 0, 0, 0))
                .build();
        var model3 = BlogPostDomainModel.builder()
                .id("testingId1")
                .title("testingTitle1")
                .summary("testingSummary1")
                .content("testingContent1")
                .authorId("testingAuthorId1")
                .tagList(List.of("Java", "SpringBoot", "Unit testing"))
                .coverImageUrl("testing.jpg")
                .contentType(BlogPostContentType.MARKDOWN)
                .contentFileName("testing1.md")
                .createdDateTime(LocalDateTime.of(2025, 3, 18, 0, 0, 0))
                .build();
        var summaryList = blogPostMapper.toBlogPostSummaryDtoList(List.of(model, model2, model3));
        Assertions.assertNotNull(summaryList);
        Assertions.assertEquals(3, summaryList.size());
    }

    @Test
    void toBlogPostSummaryDto() {
        var model = BlogPostDomainModel.builder()
                .id("testingId1")
                .title("testingTitle1")
                .summary("testingSummary1")
                .content("testingContent1")
                .authorId("testingAuthorId1")
                .tagList(List.of("Java", "SpringBoot", "Unit testing"))
                .coverImageUrl("testing.jpg")
                .contentType(BlogPostContentType.MARKDOWN)
                .contentFileName("testing1.md")
                .createdDateTime(LocalDateTime.of(2025, 3, 18, 0, 0, 0))
                .build();

        var summaryDto = blogPostMapper.toBlogPostSummaryDto(model);
        Assertions.assertNotNull(summaryDto);
        Assertions.assertEquals("testingId1", summaryDto.getId());
        Assertions.assertEquals("testingTitle1", summaryDto.getTitle());
        Assertions.assertEquals("testingSummary1", summaryDto.getSummary());
        Assertions.assertEquals("testingContent1", summaryDto.getContent());
        Assertions.assertEquals(List.of("Java", "SpringBoot", "Unit testing"), summaryDto.getTagList());
        Assertions.assertEquals(LocalDateTime.of(2025, 3, 18, 0, 0, 0), summaryDto.getCreatedDateTime());
    }

    @Test
    void toBlogPostSummaryDto_nullCase() {
        var summaryDto = blogPostMapper.toBlogPostSummaryDto(null);
        Assertions.assertNull(summaryDto);
    }
}