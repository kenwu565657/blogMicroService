package com.contentfarm.persistence.service;

import com.contentfarm.contentfarmdomain.aggregateroot.blogpost.BlogPostDomainModel;
import com.contentfarm.persistence.dao.BlogPostDao;
import com.contentfarm.persistence.entity.BlogPostEntity;
import com.contentfarm.persistence.mapper.BlogPostDomainModelMapper;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class BlogPostPersistenceQueryServiceTest {

    @MockitoBean
    BlogPostDao blogPostDao;

    @Autowired
    BlogPostPersistenceQueryService blogPostPersistenceQueryService;

    @BeforeEach
    void init() {
        Mockito.lenient().when(blogPostDao.getById(TestConstant.TESTING_ID_1))
                .thenReturn(getBlogPostEntityList().get(0));
    }

    @Nested
    class testGetById {

        @Test
        void testGetByIdHappyCase() {
            BlogPostDomainModel blogPostDomainModel = blogPostPersistenceQueryService.getById(TestConstant.TESTING_ID_1);

            verify(blogPostDao, times(1)).getById(TestConstant.TESTING_ID_1);
            Assertions.assertEquals(TestConstant.TESTING_ID_1, blogPostDomainModel.getId());
        }

        @Test
        void testGetByIdUnHappyEmptyCase() {
            BlogPostDomainModel blogPostDomainModel = blogPostPersistenceQueryService.getById(TestConstant.NON_EXISTING_ID);

            verify(blogPostDao, times(1)).getById(TestConstant.NON_EXISTING_ID);
            Assertions.assertNull(blogPostDomainModel);
        }
    }

    private static class TestConstant {
        private static final String TESTING_ID_1 = "testing_id_1";
        private static final String TESTING_ID_2 = "testing_id_2";
        private static final String TESTING_ID_3 = "testing_id_3";
        private static final String NON_EXISTING_ID = "non_existing_id";
    }

    private List<BlogPostEntity> getBlogPostEntityList() {
        BlogPostEntity blogPostEntity1 = BlogPostEntity
                .builder()
                .id(TestConstant.TESTING_ID_1)
                .build();
        BlogPostEntity blogPostEntity2 = BlogPostEntity
                .builder()
                .id(TestConstant.TESTING_ID_2)
                .build();
        BlogPostEntity blogPostEntity3 = BlogPostEntity
                .builder()
                .id(TestConstant.TESTING_ID_3)
                .build();

        return List.of(blogPostEntity1, blogPostEntity2, blogPostEntity3);
    }

}