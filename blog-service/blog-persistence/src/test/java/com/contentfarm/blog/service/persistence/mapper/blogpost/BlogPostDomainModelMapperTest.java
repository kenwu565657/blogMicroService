package com.contentfarm.blog.service.persistence.mapper.blogpost;

import com.contentfarm.blog.service.domain.aggregateroot.blogpost.BlogPostDomainModel;
import com.contentfarm.blog.service.persistence.entity.blogpost.BlogPostEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class BlogPostDomainModelMapperTest {
    @Test
    void mapToBlogPostDomainModels() {
        BlogPostDomainModelMapper mapper = new BlogPostDomainModelMapper();
        BlogPostEntity blogPostEntity1 = new BlogPostEntity();
        blogPostEntity1.setId("testingId1");
        blogPostEntity1.setContentFileName("testingContent1.md");
        blogPostEntity1.setTitle("testingTitle1");
        BlogPostEntity blogPostEntity2 = new BlogPostEntity();
        blogPostEntity2.setId("testingId2");
        blogPostEntity2.setContentFileName("testingContent2.md");
        blogPostEntity2.setTitle("testingTitle2");
        List<BlogPostEntity> blogPostEntityList = List.of(blogPostEntity1, blogPostEntity2);
        List<BlogPostDomainModel> blogPostDomainModelList = mapper.mapToBlogPostDomainModels(blogPostEntityList);
        Assertions.assertEquals(2, blogPostDomainModelList.size());
    }

    @Test
    void mapToBlogPostDomainModel() {
        BlogPostDomainModelMapper mapper = new BlogPostDomainModelMapper();
        BlogPostEntity blogPostEntity = new BlogPostEntity();
        blogPostEntity.setId("testingId1");
        blogPostEntity.setContentFileName("testingContent1.md");
        blogPostEntity.setTitle("testingTitle1");
        BlogPostDomainModel blogPostDomainModel = mapper.mapToBlogPostDomainModel(blogPostEntity);
        Assertions.assertNotNull(blogPostDomainModel);
        Assertions.assertEquals("testingId1", blogPostDomainModel.getId());
        Assertions.assertEquals("testingContent1.md", blogPostDomainModel.getContentFileName());
        Assertions.assertEquals("testingTitle1", blogPostDomainModel.getTitle());
    }

    @Test
    void mapToBlogPostDomainModel_null() {
        BlogPostDomainModelMapper mapper = new BlogPostDomainModelMapper();
        BlogPostDomainModel blogPostDomainModel = mapper.mapToBlogPostDomainModel(null);
        Assertions.assertNull(blogPostDomainModel);
    }
}