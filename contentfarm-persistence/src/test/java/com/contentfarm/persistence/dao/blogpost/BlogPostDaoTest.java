package com.contentfarm.persistence.dao.blogpost;

import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class BlogPostDaoTest {

    private Integer numberOfRecords = 0;

    @Inject
    BlogPostDao blogPostDao;

    @BeforeAll
    void setUp() {
        var result = blogPostDao.count();
        numberOfRecords = Math.toIntExact(result);
        log.info("Number of records: {}", numberOfRecords);
    }

    @Test
    void testGetBlogPostById() {
        var result = blogPostDao.findAll();
        Assertions.assertEquals(result.size(), (int) numberOfRecords);
    }

    @Test
    void testFindByAuthorId() {
        var result = blogPostDao.findByAuthorId("testing_id");
        Assertions.assertEquals(result.size(), (int) numberOfRecords);
    }

}