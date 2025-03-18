package com.contentfarm.blog.service.web.controller.blogpost;

import com.contentfarm.blog.service.domain.aggregateroot.blogpost.BlogPostDomainModel;
import com.contentfarm.blog.service.domain.inputport.web.blogpost.IBlogPostWebDomainService;
import com.contentfarm.blog.service.domain.inputport.web.tag.IBlogPostTagWebDomainService;
import com.contentfarm.constant.blogpost.BlogPostContentType;
import com.contentfarm.utils.ContentFarmFileTypeConvertUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HexFormat;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication(scanBasePackages = {"com.contentfarm.blog.service.web"})
public class BlogPostControllerTestConfiguration {

    @Bean
    IBlogPostWebDomainService blogPostDomainService() {
        return new IBlogPostWebDomainServiceSpy();
    }

    @Bean
    IBlogPostTagWebDomainService blogPostTagDomainService() {
        return new IBlogPostTagWebDomainServiceSpy();
    }

    public static class IBlogPostWebDomainServiceSpy implements IBlogPostWebDomainService {
        private final byte[] defaultByteArray = HexFormat.of().parseHex("e04fd020ea3a6910a2d808002b30309d");
        private final List<String> validIdList = List.of("testingId1", "testingId2", "testingId3");
        private final List<String> validKeyList = List.of("testing1.md", "testing2.md", "testing3.md");
        private final List<BlogPostDomainModel> blogPostDomainModelList = List.of(
                BlogPostDomainModel.builder()
                        .id("testingId1")
                        .title("testingTitle1")
                        .summary("testingSummary1")
                        .content("testingContent1")
                        .authorId("testingAuthorId1")
                        .tagList(List.of("Java", "SpringBoot", "Unit testing"))
                        .coverImageUrl("testing.jpg")
                        .contentType(BlogPostContentType.MARKDOWN)
                        .contentFileName("testing1.md")
                        .createdDateTime(LocalDateTime.of(2025, 3, 18, 0, 0, 0))
                        .build(),
                BlogPostDomainModel.builder().id("testingId2").authorId("testingAuthorId2").build(),
                BlogPostDomainModel.builder().id("testingId3").authorId("testingAuthorId1").build()
        );

        @Override
        public List<BlogPostDomainModel> findBlogPostByAuthorId(String authorId) {
            return blogPostDomainModelList.stream().filter(x -> x.getAuthorId().equals(authorId)).collect(Collectors.toList());
        }

        @Override
        public BlogPostDomainModel getBlogPostById(String id) {
            return blogPostDomainModelList.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
        }

        @Override
        public String getBlogPostContentAsHtmlByFileName(String key) {
            if (validKeyList.contains(key)) {
                return ContentFarmFileTypeConvertUtils.markdownToHtml(new String(defaultByteArray, StandardCharsets.UTF_8));
            }
            return null;
        }

        @Override
        public byte[] getBlogPostContentAsMarkdownByFileName(String key) {
            if (validKeyList.contains(key)) {
                return defaultByteArray;
            }
            return null;
        }

        @Override
        public byte[] getBlogPostContentAsMarkdownById(String Id) {
            if (validIdList.contains(Id)) {
                return defaultByteArray;
            }
            return null;
        }
    }

    public static class IBlogPostTagWebDomainServiceSpy implements IBlogPostTagWebDomainService {
        private final List<String> tagList = new ArrayList<>();

        @Override
        public List<String> findTagList() {
            return tagList;
        }

        @Override
        public void addBlogPostTag(List<String> blogPostTagNameList) {
            tagList.addAll(blogPostTagNameList);
        }

        @Override
        public void deleteBlogPostTag(List<String> blogPostTagNameList) {
            tagList.removeAll(blogPostTagNameList);
        }
    }
}
