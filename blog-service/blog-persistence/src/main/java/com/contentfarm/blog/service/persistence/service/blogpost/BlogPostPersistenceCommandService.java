package com.contentfarm.blog.service.persistence.service.blogpost;

import com.contentfarm.blog.service.domain.aggregateroot.blogpost.BlogPostDomainModel;
import com.contentfarm.blog.service.domain.outputport.persistence.IBlogPostPersistenceCommandService;
import com.contentfarm.blog.service.persistence.dao.blogpost.BlogPostDao;
import com.contentfarm.blog.service.persistence.dao.blogpost.BlogPostTagDao;
import com.contentfarm.blog.service.persistence.entity.blogpost.BlogPostTagEntity;
import com.contentfarm.blog.service.persistence.mapper.blogpost.BlogPostDomainModelMapper;
import com.contentfarm.utils.ContentFarmLocaleDateTimeUtils;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BlogPostPersistenceCommandService implements IBlogPostPersistenceCommandService {

    private final BlogPostDao blogPostDao;
    private final BlogPostTagDao blogPostTagDao;
    private final BlogPostDomainModelMapper blogPostDomainModelMapper;

    public BlogPostPersistenceCommandService(BlogPostDao blogPostDao, BlogPostTagDao blogPostTagDao, BlogPostDomainModelMapper blogPostDomainModelMapper) {
        this.blogPostDao = blogPostDao;
        this.blogPostTagDao = blogPostTagDao;
        this.blogPostDomainModelMapper = blogPostDomainModelMapper;
    }

    @Override
    public BlogPostDomainModel upsertBlogPost(BlogPostDomainModel blogPostDomainModel) {
        var blogPostEntity = blogPostDomainModelMapper.mapToBlogPostEntity(blogPostDomainModel);
        if (null == blogPostEntity.getId()) {
            String uuid = UUID.randomUUID().toString();
            blogPostEntity.setId(uuid);
            blogPostDomainModel.setId(uuid);
        } else {
            blogPostEntity.setLastUpdatedDateTime(ContentFarmLocaleDateTimeUtils.ofNow());
        }
        blogPostDao.save(blogPostEntity);
        return blogPostDomainModel;
    }

    @Override
    public String deleteBlogPostById(String id) {
        blogPostDao.deleteById(id);
        return id;
    }

    @Override
    @Transactional
    public void addBlogPostTag(List<String> blogPostTagNameList) {
        if (blogPostTagNameList == null || blogPostTagNameList.isEmpty()) {
            return;
        }
        var blogPostTagEntityList = buildBlogPostTagEntityList(blogPostTagNameList);
        blogPostTagDao.saveAll(blogPostTagEntityList);
    }

    @Override
    @Transactional
    public void deleteBlogPostTag(List<String> blogPostTagNameList) {
        if (blogPostTagNameList == null || blogPostTagNameList.isEmpty()) {
            return;
        }
        var blogPostTagEntityList = blogPostTagDao.findByTagNameIn(blogPostTagNameList);
        blogPostTagDao.deleteAll(blogPostTagEntityList);
    }

    private List<BlogPostTagEntity> buildBlogPostTagEntityList(List<String> blogPostTagNameList) {
        return blogPostTagNameList.stream().map(this::buildBlogPostTagEntity).toList();
    }

    private BlogPostTagEntity buildBlogPostTagEntity(String blogPostTagName) {
        return BlogPostTagEntity
                .builder()
                .id(UUID.randomUUID().toString())
                .tagName(blogPostTagName)
                .build();
    }
}
