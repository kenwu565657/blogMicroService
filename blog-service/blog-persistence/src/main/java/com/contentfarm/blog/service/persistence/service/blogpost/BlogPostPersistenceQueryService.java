package com.contentfarm.blog.service.persistence.service.blogpost;

import com.contentfarm.blog.service.domain.aggregateroot.blogpost.BlogPostDomainModel;
import com.contentfarm.blog.service.domain.outputport.persistence.IBlogPostPersistenceQueryService;
import com.contentfarm.blog.service.persistence.dao.blogpost.BlogPostDao;
import com.contentfarm.blog.service.persistence.dao.blogpost.BlogPostTagDao;
import com.contentfarm.blog.service.persistence.entity.blogpost.BlogPostTagEntity;
import com.contentfarm.blog.service.persistence.mapper.blogpost.BlogPostDomainModelMapper;
import com.contentfarm.file.operation.springboot.starter.service.FileStorageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@AllArgsConstructor
@Service
public class BlogPostPersistenceQueryService implements IBlogPostPersistenceQueryService {

    private final BlogPostDao blogPostDao;
    private final BlogPostTagDao blogPostTagDao;
    private final BlogPostDomainModelMapper blogPostDomainModelMapper;
    private final FileStorageService fileStorageService;

    @Override
    public List<BlogPostDomainModel> findByAuthorId(String authorId) {
        var blogPostEntityList = blogPostDao.findByAuthorId(authorId);
        return blogPostDomainModelMapper.mapToBlogPostDomainModels(blogPostEntityList);
    }

    @Override
    public byte[] getBlogPostContentByKey(String key) {
        return fileStorageService.downloadFile("contentfarmblogpost", MessageFormat.format("{0}/{1}", "blog-post-content", key));
    }

    @Override
    public List<String> findTagList() {
        return blogPostTagDao.findAll().stream().map(BlogPostTagEntity::getTagName).toList();
    }

    @Override
    public byte[] getBlogPostContentById(String id) {
        var contentFileNameProjection = blogPostDao.getContentFileNameById(id);
        var contentFileName = contentFileNameProjection.getContentFileName();
        return getBlogPostContentByKey(contentFileName);
    }
}
