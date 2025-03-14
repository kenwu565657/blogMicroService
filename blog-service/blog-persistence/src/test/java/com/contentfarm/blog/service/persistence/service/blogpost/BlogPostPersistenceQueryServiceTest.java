package com.contentfarm.blog.service.persistence.service.blogpost;

import com.contentfarm.blog.service.persistence.TestPostgreSQLContainer;
import com.contentfarm.blog.service.persistence.dao.blogpost.BlogPostDao;
import com.contentfarm.blog.service.persistence.dao.blogpost.BlogPostTagDao;
import com.contentfarm.blog.service.persistence.entity.blogpost.BlogPostEntity;
import com.contentfarm.blog.service.persistence.entity.blogpost.BlogPostTagEntity;
import com.contentfarm.file.operation.springboot.starter.service.FileStorageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.context.ImportTestcontainers;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDateTime;
import java.util.HexFormat;
import java.util.List;

@ImportTestcontainers(TestPostgreSQLContainer.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = {BlogPostPersistenceQueryServiceTestConfiguration.class})
class BlogPostPersistenceQueryServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(BlogPostPersistenceQueryServiceTest.class);

    @Autowired
    private BlogPostPersistenceQueryService blogPostPersistenceQueryService;

    @MockitoBean
    BlogPostDao blogPostDao;

    @MockitoBean
    FileStorageService fileStorageService;

    @MockitoBean
    BlogPostTagDao blogPostTagDao;

    @BeforeEach
    void beforeEach() {
        var testingBlogPostDataList = getTestingBlogPostEntityDataList();
        Mockito.when(blogPostDao.findAll()).thenReturn(testingBlogPostDataList);
        Mockito.when(blogPostDao.getContentFileNameById("testingId1")).thenReturn(new ContentFileNameProjectionStub("testingFileName1.md"));
        Mockito.when(blogPostDao.getContentFileNameById("testingId2")).thenReturn(new ContentFileNameProjectionStub("testingFileName2.md"));
        Mockito.when(blogPostDao.getContentFileNameById("testingId3")).thenReturn(new ContentFileNameProjectionStub("testingFileName3.md"));

        var testingBlogPostTagDataList = getTestingBlogPostTagEntityDataList();
        Mockito.when(blogPostTagDao.findAll()).thenReturn(testingBlogPostTagDataList);

        Mockito.when(fileStorageService.downloadFile("contentfarmblogpost", "blog-post-content/testingFileName1.md")).thenReturn(HexFormat.of().parseHex("e04fd020ea3a6910a2d808002b30309d"));
        Mockito.when(fileStorageService.downloadFile("contentfarmblogpost", "blog-post-content/testingFileName2.md")).thenReturn(HexFormat.of().parseHex("e04fd020ea3a6910a2d808002b30309d"));
        Mockito.when(fileStorageService.downloadFile("contentfarmblogpost", "blog-post-content/testingFileName3.md")).thenReturn(HexFormat.of().parseHex("e04fd020ea3a6910a2d808002b30309d"));
    }

    @Test
    void getBlogPostContentByKey() {
        String key = "testingFileName1.md";
        var content = blogPostPersistenceQueryService.getBlogPostContentByKey(key);
        Assertions.assertNotNull(content);
        Assertions.assertTrue(content.length > 0);

        String key2 = "testingFileName4.md";
        var content2 = blogPostPersistenceQueryService.getBlogPostContentByKey(key2);
        Assertions.assertNull(content2);
    }

    @Test
    void testGetBlogPostContentById() {
        var result = blogPostPersistenceQueryService.getBlogPostContentById("testingId1");
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.length > 0);
        var result2 = blogPostPersistenceQueryService.getBlogPostContentById("testingId2");
        Assertions.assertNotNull(result2);
        Assertions.assertTrue(result2.length > 0);
        var result3 = blogPostPersistenceQueryService.getBlogPostContentById("testingId3");
        Assertions.assertNotNull(result3);
        Assertions.assertTrue(result3.length > 0);
    }

    private List<BlogPostTagEntity> getTestingBlogPostTagEntityDataList() {
        return List.of(
                BlogPostTagEntity.builder().id("testingId1").tagName("Java").build(),
                BlogPostTagEntity.builder().id("testingId2").tagName("SpringBoot").build(),
                BlogPostTagEntity.builder().id("testingId3").tagName("Unit Testing").build()
        );
    }

    private List<BlogPostEntity> getTestingBlogPostEntityDataList() {
        return List.of(
                createTestingBlogPostEntityData("testingId1", "testingAuthor1", "testingFileName1.md"),
                createTestingBlogPostEntityData("testingId2", "testingAuthor2", "testingFileName2.md"),
                createTestingBlogPostEntityData("testingId3", "testingAuthor1", "testingFileName3.md")
        );
    }

    private BlogPostEntity createTestingBlogPostEntityData(String id, String authorId, String contentFileName) {
        return BlogPostEntity
                .builder()
                .id(id)
                .authorId(authorId)
                .contentFileName(contentFileName)
                .title("testingTitle")
                .createdDateTime(LocalDateTime.now())
                .build();
    }

    private record ContentFileNameProjectionStub(String contentFileName) implements BlogPostDao.ContentFileNameProjection {
        @Override
        public String getContentFileName() {
            return contentFileName;
        }
    }
}
