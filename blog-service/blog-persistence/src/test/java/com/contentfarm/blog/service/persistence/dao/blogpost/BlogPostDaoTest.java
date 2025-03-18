package com.contentfarm.blog.service.persistence.dao.blogpost;

import com.contentfarm.blog.service.persistence.TestPostgreSQLContainer;
import com.contentfarm.blog.service.persistence.entity.blogpost.BlogPostEntity;
import com.contentfarm.constant.blogpost.BlogPostContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.context.ImportTestcontainers;

import java.time.LocalDateTime;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ImportTestcontainers(TestPostgreSQLContainer.class)
@SpringBootTest(classes = BlogPostDaoTestConfiguration.class)
class BlogPostDaoTest {
    @Autowired
    BlogPostDao blogPostDao;

    private static final Logger logger = LoggerFactory.getLogger(BlogPostDaoTest.class);

    private Integer numberOfRecords = 0;

    @BeforeAll
    void beforeAll() {
        blogPostDao.deleteAll();
        var testingData = getTestingDataList();
        blogPostDao.saveAll(testingData);
        var result = blogPostDao.count();
        numberOfRecords = Math.toIntExact(result);
        logger.info("Number of records: {}", numberOfRecords);
    }

    @Test
    void testGetBlogPostById() {
        var result = blogPostDao.findAll();
        Assertions.assertEquals(result.size(), (int) numberOfRecords);
    }

    @Test
    void testFindByAuthorId() {
        var result = blogPostDao.findByAuthorId("testingAuthor1");
        Assertions.assertEquals(2, result.size());
        var result2 = blogPostDao.findByAuthorId("testingAuthor2");
        Assertions.assertEquals(1, result2.size());
        var result3 = blogPostDao.findByAuthorId("testingAuthor3");
        Assertions.assertEquals(0, result3.size());
    }

    @Test
    void testGetContentFileNameById() {
        var result = blogPostDao.getContentFileNameById("testingId1");
        Assertions.assertEquals("testingFileName1.md", result.getContentFileName());
        var result2 = blogPostDao.getContentFileNameById("testingId2");
        Assertions.assertEquals("testingFileName2.md", result2.getContentFileName());
        var result3 = blogPostDao.getContentFileNameById("testingId3");
        Assertions.assertEquals("testingFileName3.md", result3.getContentFileName());
    }

    private List<BlogPostEntity> getTestingDataList() {
        return List.of(
                createTestingData("testingId1", "testingAuthor1", "testingFileName1.md"),
                createTestingData("testingId2", "testingAuthor2", "testingFileName2.md"),
                createTestingData("testingId3", "testingAuthor1", "testingFileName3.md")
        );
    }

    private BlogPostEntity createTestingData(String id, String authorId, String contentFileName) {
        return BlogPostEntity
                .builder()
                .id(id)
                .authorId(authorId)
                .contentType(BlogPostContentType.MARKDOWN)
                .contentFileName(contentFileName)
                .title("testingTitle")
                .createdDateTime(LocalDateTime.now())
                .build();
    }
}