package com.contentfarm.blog.service.persistence.service.blogpost;

import com.contentfarm.blog.service.persistence.TestPostgreSQLContainer;
import com.contentfarm.blog.service.persistence.dao.blogpost.BlogPostTagDao;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ImportTestcontainers(TestPostgreSQLContainer.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = {BlogPostPersistenceQueryServiceTestConfiguration.class})
class BlogPostPersistenceCommandServiceTest {

    @Autowired
    BlogPostPersistenceCommandService blogPostPersistenceCommandService;

    @Autowired
    BlogPostTagDao blogPostTagDao;

    @BeforeAll
    void setUp() {
        blogPostTagDao.deleteAll();
    }

    @Test
    @Order(1)
    void addBlogPostTag() {
        long count = blogPostTagDao.count();
        Assertions.assertEquals(0, count);
        List<String> blogPostTagList = List.of("Java", "TypeScript", "Testing");
        Assertions.assertDoesNotThrow(() -> blogPostPersistenceCommandService.addBlogPostTag(blogPostTagList));
        count = blogPostTagDao.count();
        Assertions.assertEquals(blogPostTagList.size(), count);
    }

    @Test
    @Order(2)
    void deleteBlogPostTag() {
        long count = blogPostTagDao.count();
        Assertions.assertEquals(3, count);
        List<String> blogPostTagList = List.of("Java", "TypeScript", "Testing");
        Assertions.assertDoesNotThrow(() -> blogPostPersistenceCommandService.deleteBlogPostTag(blogPostTagList));
        count = blogPostTagDao.count();
        Assertions.assertEquals(0, count);
    }
}