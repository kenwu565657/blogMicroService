package com.contentfarm.blog.service.domain.domainservice.blogpost;

import com.contentfarm.blog.service.domain.aggregateroot.blogpost.BlogPostDomainModel;
import com.contentfarm.blog.service.domain.inputport.mq.blogpost.IBlogPostMessageQueueDomainService;
import com.contentfarm.blog.service.domain.inputport.web.blogpost.IBlogPostWebDomainService;
import com.contentfarm.blog.service.domain.outputport.persistence.IBlogPostPersistenceCommandService;
import com.contentfarm.blog.service.domain.outputport.persistence.IBlogPostPersistenceQueryService;
import com.contentfarm.utils.ContentFarmFileTypeConvertUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogPostDomainService implements IBlogPostWebDomainService, IBlogPostMessageQueueDomainService {
    private final IBlogPostPersistenceQueryService blogPostPersistenceQueryService;
    private final IBlogPostPersistenceCommandService blogPostPersistenceCommandService;

    @Override
    public List<BlogPostDomainModel> findBlogPostByAuthorId(String authorId) {
        return blogPostPersistenceQueryService.findByAuthorId(authorId);
    }

    @Override
    public String getBlogPostContentByKey(String key) {
        byte[] blogPostContent = getBlogPostContentAsMarkdownByKey(key);
        return ContentFarmFileTypeConvertUtils.markdownToHtml(new String(blogPostContent, StandardCharsets.UTF_8));
    }

    @Override
    public byte[] getBlogPostContentAsMarkdownByKey(String key) {
        return blogPostPersistenceQueryService.getBlogPostContentByKey(key);
    }

    @Override
    public byte[] getBlogPostContentAsMarkdownById(String Id) {
        return blogPostPersistenceQueryService.getBlogPostContentById(Id);
    }

    @Override
    public BlogPostDomainModel upsertBlogPost(BlogPostDomainModel blogPostDomainModel) {
        return blogPostPersistenceCommandService.upsertBlogPost(blogPostDomainModel);
    }

    @Override
    public String deleteBlogPostById(String Id) {
        return blogPostPersistenceCommandService.deleteBlogPostById(Id);
    }
}
