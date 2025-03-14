package com.contentfarm.search.service.blogpost.impl;

import com.contentfarm.search.TestElasticSearchContainer;
import com.contentfarm.search.document.blogpost.BlogPostDocument;
import com.contentfarm.search.exception.DocumentIndexException;
import com.contentfarm.search.repository.blogpost.BlogPostElasticsearchRepository;
import com.contentfarm.search.service.blogpost.IBlogPostIndexService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.context.ImportTestcontainers;

import java.text.MessageFormat;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ImportTestcontainers(TestElasticSearchContainer.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class BlogPostIndexServiceTest {

    @Autowired
    IBlogPostIndexService blogPostIndexService;

    @Autowired
    BlogPostElasticsearchRepository blogPostElasticsearchRepository;

    @BeforeAll
    void setUp() {
        blogPostElasticsearchRepository.deleteAll();
    }

    @Test
    @Order(1)
    void addDocument() {
        var document = buildNormalTestBlogPostDocument(0);
        Assertions.assertDoesNotThrow(() -> blogPostIndexService.addDocument(document));
        var isExist = blogPostElasticsearchRepository.existsById("testingId0");
        Assertions.assertTrue(isExist);
        var count = blogPostElasticsearchRepository.count();
        Assertions.assertEquals(1, count);
        var result = blogPostElasticsearchRepository.findById("testingId0");
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("testingId0", result.get().getId());
        Assertions.assertEquals("testingTitle0", result.get().getTitle());
        Assertions.assertEquals(List.of("testingTag1", "testingTag2"), result.get().getTagList());
    }

    @Test
    @Order(2)
    void addDocument_sameIdReIndex() {
        var document = buildNormalTestBlogPostDocument(0);
        document.setTitle("newTestingTitle1");
        document.setTagList(List.of("newTestingTag1", "newTestingTag2"));
        Assertions.assertDoesNotThrow(() -> blogPostIndexService.addDocument(document));
        var isExist = blogPostElasticsearchRepository.existsById("testingId0");
        Assertions.assertTrue(isExist);
        var count = blogPostElasticsearchRepository.count();
        Assertions.assertEquals(1, count);
        var result = blogPostElasticsearchRepository.findById("testingId0");
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("testingId0", result.get().getId());
        Assertions.assertEquals("newTestingTitle1", result.get().getTitle());
        Assertions.assertEquals(List.of("newTestingTag1", "newTestingTag2"), result.get().getTagList());
    }

    @Test
    @Order(3)
    void addDocument_differentId() {
        var document = buildNormalTestBlogPostDocument(1);
        Assertions.assertDoesNotThrow(() -> blogPostIndexService.addDocument(document));
        var isExist = blogPostElasticsearchRepository.existsById("testingId1");
        Assertions.assertTrue(isExist);
        var count = blogPostElasticsearchRepository.count();
        Assertions.assertEquals(2, count);
    }

    @Test
    @Order(4)
    void addDocument_nullTagList() {
        var document = buildNormalTestBlogPostDocument(2);
        document.setTagList(null);
        Assertions.assertDoesNotThrow(() -> blogPostIndexService.addDocument(document));
        var isExist = blogPostElasticsearchRepository.existsById("testingId2");
        Assertions.assertTrue(isExist);
        var count = blogPostElasticsearchRepository.count();
        Assertions.assertEquals(3, count);
        var result = blogPostElasticsearchRepository.findById("testingId2");
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("testingId2", result.get().getId());
        Assertions.assertTrue(result.get().getTagList().isEmpty());
    }

    @Test
    void addDocument_nullCase() {
        DocumentIndexException documentIndexException = Assertions.assertThrows(DocumentIndexException.class, () -> blogPostIndexService.addDocument(null));
        Assertions.assertNotNull(documentIndexException);
        Assertions.assertEquals("Can Not Index Null Document.", documentIndexException.getMessage());
    }

    @Test
    void addDocument_emptyBean() {
        var document = BlogPostDocument.builder().build();
        DocumentIndexException documentIndexException = Assertions.assertThrows(DocumentIndexException.class, () -> blogPostIndexService.addDocument(document));
        Assertions.assertNotNull(documentIndexException);
        Assertions.assertEquals("Found Missing field(s): Id, Title, Summary, Author Name, Post Date, Image Url.", documentIndexException.getMessage());
    }

    @Test
    void addDocument_blankId() {
        var document = buildNormalTestBlogPostDocument(0);
        document.setId(" ");
        DocumentIndexException documentIndexException = Assertions.assertThrows(DocumentIndexException.class, () -> blogPostIndexService.addDocument(document));
        Assertions.assertNotNull(documentIndexException);
        Assertions.assertEquals("Found Missing field(s): Id.", documentIndexException.getMessage());

        var document2 = buildNormalTestBlogPostDocument(0);
        document2.setId("");
        DocumentIndexException documentIndexException2 = Assertions.assertThrows(DocumentIndexException.class, () -> blogPostIndexService.addDocument(document2));
        Assertions.assertNotNull(documentIndexException2);
        Assertions.assertEquals("Found Missing field(s): Id.", documentIndexException2.getMessage());
    }

    @Test
    void addDocument_blankFields() {
        var document = buildNormalTestBlogPostDocument(0);
        document.setId("");
        document.setTitle("");
        document.setSummary("");
        document.setAuthorName("");
        document.setImageUrl("");
        document.setPostDate("");
        DocumentIndexException documentIndexException = Assertions.assertThrows(DocumentIndexException.class, () -> blogPostIndexService.addDocument(document));
        Assertions.assertNotNull(documentIndexException);
        Assertions.assertEquals("Found Missing field(s): Id, Title, Summary, Author Name, Post Date, Image Url.", documentIndexException.getMessage());
    }

    @Test
    void addDocument_spaceFields() {
        var document = buildNormalTestBlogPostDocument(0);
        document.setId("  ");
        document.setTitle(" ");
        document.setSummary(" ");
        document.setAuthorName("   ");
        document.setImageUrl(" ");
        document.setPostDate(" ");
        DocumentIndexException documentIndexException = Assertions.assertThrows(DocumentIndexException.class, () -> blogPostIndexService.addDocument(document));
        Assertions.assertNotNull(documentIndexException);
        Assertions.assertEquals("Found Missing field(s): Id, Title, Summary, Author Name, Post Date, Image Url.", documentIndexException.getMessage());
    }

    private BlogPostDocument buildNormalTestBlogPostDocument(int orderNumber) {
        return BlogPostDocument
                .builder()
                .id(MessageFormat.format("testingId{0}", orderNumber))
                .title(MessageFormat.format("testingTitle{0}", orderNumber))
                .tagList(List.of("testingTag1", "testingTag2"))
                .summary(MessageFormat.format("This is a testingSummary{0} keyword", orderNumber))
                .authorName(MessageFormat.format("testingAuthor{0}", orderNumber))
                .imageUrl(MessageFormat.format("testingImage{0}", orderNumber))
                .postDate(MessageFormat.format("testingPostDate{0}", orderNumber))
                .build();
    }
}
