package com.contentfarm.blog.service.domain.domainservice.blogpost;

import com.contentfarm.blog.service.domain.aggregateroot.blogpost.BlogPostDomainModel;
import com.contentfarm.blog.service.domain.outputport.persistence.IBlogPostPersistenceCommandService;
import com.contentfarm.blog.service.domain.outputport.persistence.IBlogPostPersistenceQueryService;
import com.contentfarm.utils.ContentFarmStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HexFormat;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class BlogPostDomainServiceTest {
    private static BlogPostDomainService blogPostDomainService;
    private static IBlogPostPersistenceCommandServiceSpy blogPostPersistenceCommandService;

    @BeforeAll
    static void setUp() {
        blogPostPersistenceCommandService = getBlogPostPersistenceCommandService();
        blogPostDomainService = getBlogPostDomainService();
    }

    @Test
    void findBlogPostByAuthorId() {
        var blogPostDomainModelList1 = blogPostDomainService.findBlogPostByAuthorId("testingAuthorId1");
        assertEquals(2, blogPostDomainModelList1.size());

        var blogPostDomainModelList2 = blogPostDomainService.findBlogPostByAuthorId("testingAuthorId2");
        assertEquals(1, blogPostDomainModelList2.size());

        var blogPostDomainModelList3 = blogPostDomainService.findBlogPostByAuthorId("testingAuthorId3");
        assertEquals(0, blogPostDomainModelList3.size());
    }

    @Test
    void getBlogPostContentByKey() {
        var blogPostContent = blogPostDomainService.getBlogPostContentByKey("testing1.md");
        Assertions.assertNotNull(blogPostContent);
        Assertions.assertFalse(ContentFarmStringUtils.isBlank(blogPostContent));

        blogPostContent = blogPostDomainService.getBlogPostContentByKey("testing2.md");
        Assertions.assertNotNull(blogPostContent);
        Assertions.assertFalse(ContentFarmStringUtils.isBlank(blogPostContent));

        blogPostContent = blogPostDomainService.getBlogPostContentByKey("testing3.md");
        Assertions.assertNotNull(blogPostContent);
        Assertions.assertFalse(ContentFarmStringUtils.isBlank(blogPostContent));
    }

    @Test
    void getBlogPostContentAsMarkdownByKey() {
        var blogPostContent = blogPostDomainService.getBlogPostContentAsMarkdownByKey("testing1.md");
        Assertions.assertNotNull(blogPostContent);
        Assertions.assertTrue(blogPostContent.length > 0);

        blogPostContent = blogPostDomainService.getBlogPostContentAsMarkdownByKey("testing2.md");
        Assertions.assertNotNull(blogPostContent);
        Assertions.assertTrue(blogPostContent.length > 0);

        blogPostContent = blogPostDomainService.getBlogPostContentAsMarkdownByKey("testing3.md");
        Assertions.assertNotNull(blogPostContent);
        Assertions.assertTrue(blogPostContent.length > 0);
    }

    @Test
    void getBlogPostContentAsMarkdownById() {
        var blogPostContent = blogPostDomainService.getBlogPostContentAsMarkdownById("testingId1");
        Assertions.assertNotNull(blogPostContent);
        Assertions.assertTrue(blogPostContent.length > 0);

        blogPostContent = blogPostDomainService.getBlogPostContentAsMarkdownById("testingId2");
        Assertions.assertNotNull(blogPostContent);
        Assertions.assertTrue(blogPostContent.length > 0);

        blogPostContent = blogPostDomainService.getBlogPostContentAsMarkdownById("testingId3");
        Assertions.assertNotNull(blogPostContent);
        Assertions.assertTrue(blogPostContent.length > 0);
    }

    private static IBlogPostPersistenceCommandServiceSpy getBlogPostPersistenceCommandService() {
        return Objects.requireNonNullElseGet(blogPostPersistenceCommandService, IBlogPostPersistenceCommandServiceSpy::new);
    }

    private static BlogPostDomainService getBlogPostDomainService() {
        return Objects.requireNonNullElseGet(blogPostDomainService, () -> new BlogPostDomainService(new IBlogPostPersistenceQueryServiceSpy(), getBlogPostPersistenceCommandService()));
    }

    private static class IBlogPostPersistenceCommandServiceSpy implements IBlogPostPersistenceCommandService {

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

        }

        @Override
        public void deleteBlogPostTag(List<String> blogPostTagNameList) {

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