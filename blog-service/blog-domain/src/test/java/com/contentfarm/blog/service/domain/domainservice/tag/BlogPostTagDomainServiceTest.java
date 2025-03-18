package com.contentfarm.blog.service.domain.domainservice.tag;

import com.contentfarm.blog.service.domain.aggregateroot.blogpost.BlogPostDomainModel;
import com.contentfarm.blog.service.domain.outputport.persistence.IBlogPostPersistenceCommandService;
import com.contentfarm.blog.service.domain.outputport.persistence.IBlogPostPersistenceQueryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.HexFormat;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

class BlogPostTagDomainServiceTest {
    private static BlogPostTagDomainService blogPostTagDomainService;
    private static IBlogPostPersistenceCommandServiceSpy blogPostPersistenceCommandService;

    @BeforeAll
    static void setUp() {
        blogPostPersistenceCommandService = getBlogPostPersistenceCommandService();
        blogPostTagDomainService = getBlogPostDomainService();
    }

    private static IBlogPostPersistenceCommandServiceSpy getBlogPostPersistenceCommandService() {
        return Objects.requireNonNullElseGet(blogPostPersistenceCommandService, IBlogPostPersistenceCommandServiceSpy::new);
    }

    private static BlogPostTagDomainService getBlogPostDomainService() {
        return Objects.requireNonNullElseGet(blogPostTagDomainService, () -> new BlogPostTagDomainService(new IBlogPostPersistenceQueryServiceSpy(), getBlogPostPersistenceCommandService()));
    }

    @Test
    void findTagList() {
        var tagList = blogPostTagDomainService.findTagList();
        Assertions.assertNotNull(tagList);
        Assertions.assertEquals(3, tagList.size());
        Assertions.assertTrue(tagList.contains("Java"));
        Assertions.assertTrue(tagList.contains("SpringBoot"));
        Assertions.assertTrue(tagList.contains("Unit testing"));
    }

    @Test
    void addBlogPostTag() {
        List<String> blogPostTagList = List.of("Java", "Spring");
        blogPostTagDomainService.addBlogPostTag(blogPostTagList);
        Assertions.assertEquals(blogPostPersistenceCommandService.getFakeDb().size(), blogPostTagList.size());
    }

    @Test
    void deleteBlogPostTag() {
        List<String> blogPostTagList = List.of("Java", "Spring");
        blogPostTagDomainService.deleteBlogPostTag(blogPostTagList);
        Assertions.assertEquals(0, blogPostPersistenceCommandService.getFakeDb().size());
    }

    private static class IBlogPostPersistenceCommandServiceSpy implements IBlogPostPersistenceCommandService {
        private final HashSet<String> fakeDb = new HashSet<>();

        @Override
        public BlogPostDomainModel upsertBlogPost(BlogPostDomainModel blogPostDomainModel) {
            return null;
        }

        @Override
        public String deleteBlogPostById(String id) {
            return "";
        }

        @Override
        public void addBlogPostTag(List<String> blogPostTagNameList) {
            fakeDb.addAll(blogPostTagNameList);
        }

        @Override
        public void deleteBlogPostTag(List<String> blogPostTagNameList) {
            blogPostTagNameList.forEach(fakeDb::remove);
        }

        public List<String> getFakeDb() {
            return new ArrayList<>(fakeDb);
        }
    }

    private static class IBlogPostPersistenceQueryServiceSpy implements IBlogPostPersistenceQueryService {
        private final byte[] defaultByteArray = HexFormat.of().parseHex("e04fd020ea3a6910a2d808002b30309d");
        private final List<String> validIdList = List.of("testingId1", "testingId2", "testingId3");
        private final List<String> validKeyList = List.of("testing1.md", "testing2.md", "testing3.md");
        private final List<String> tagList = List.of("Java", "SpringBoot", "Unit testing");
        private final List<BlogPostDomainModel> blogPostDomainModelList = List.of(
                BlogPostDomainModel.builder().id("testingId1").authorId("testingAuthorId1").build(),
                BlogPostDomainModel.builder().id("testingId2").authorId("testingAuthorId2").build(),
                BlogPostDomainModel.builder().id("testingId3").authorId("testingAuthorId1").build()
        );

        @Override
        public BlogPostDomainModel getById(String id) {
            return blogPostDomainModelList.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
        }

        @Override
        public List<BlogPostDomainModel> findByAuthorId(String authorId) {
            return blogPostDomainModelList.stream().filter(x -> x.getAuthorId().equals(authorId)).collect(Collectors.toList());
        }

        @Override
        public byte[] getBlogPostContentByKey(String key) {
            if (validKeyList.contains(key)) {
                return defaultByteArray;
            }
            return null;
        }

        @Override
        public List<String> findTagList() {
            return tagList;
        }

        @Override
        public byte[] getBlogPostContentById(String id) {
            if (validIdList.contains(id)) {
                return defaultByteArray;
            }
            return null;
        }
    }
}