package com.contentfarm.blog.service.persistence.dao.blogpost;

import com.contentfarm.blog.service.persistence.TestPostgreSQLContainer;
import com.contentfarm.blog.service.persistence.entity.blogpost.BlogPostTagEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.context.ImportTestcontainers;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ImportTestcontainers(TestPostgreSQLContainer.class)
@SpringBootTest(classes = BlogPostDaoTestConfiguration.class)
class BlogPostTagDaoTest {
    @Autowired
    BlogPostTagDao blogPostTagDao;

    @BeforeAll
    void beforeAll() {
        var testingData = getTestingBlogPostTagEntityDataList();
        blogPostTagDao.saveAll(testingData);
    }

    @Test
    void testFindAll() {
        var result = blogPostTagDao.findAll();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(3, result.size());
        Assertions.assertNotNull(result.stream().filter(x -> "testingId1".equals(x.getId()) && "Java".equals(x.getTagName())).findFirst().orElse(null));
        Assertions.assertNotNull(result.stream().filter(x -> "testingId2".equals(x.getId()) && "SpringBoot".equals(x.getTagName())).findFirst().orElse(null));
        Assertions.assertNotNull(result.stream().filter(x -> "testingId3".equals(x.getId()) && "Unit Testing".equals(x.getTagName())).findFirst().orElse(null));
    }

    private List<BlogPostTagEntity> getTestingBlogPostTagEntityDataList() {
        return List.of(
                BlogPostTagEntity.builder().id("testingId1").tagName("Java").build(),
                BlogPostTagEntity.builder().id("testingId2").tagName("SpringBoot").build(),
                BlogPostTagEntity.builder().id("testingId3").tagName("Unit Testing").build()
        );
    }

}