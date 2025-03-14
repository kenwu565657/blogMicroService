package com.contentfarm.blog.service.persistence.mapper.blogpost;

import com.contentfarm.blog.service.domain.aggregateroot.blogpost.BlogPostDomainModel;
import com.contentfarm.blog.service.persistence.entity.blogpost.BlogPostEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BlogPostDomainModelMapper {

    public List<BlogPostDomainModel> mapToBlogPostDomainModels(List<BlogPostEntity> blogPostEntityList) {
        if (blogPostEntityList == null || blogPostEntityList.isEmpty()) {
            return List.of();
        }
        return blogPostEntityList.stream().parallel().map(this::mapToBlogPostDomainModel).collect(Collectors.toList());
    }

    public BlogPostDomainModel mapToBlogPostDomainModel(BlogPostEntity blogPostEntity) {
        if (null == blogPostEntity) {
            return null;
        }
        return BlogPostDomainModel
                .builder()
                .id(blogPostEntity.getId())
                .title(blogPostEntity.getTitle())
                .authorId(blogPostEntity.getAuthorId())
                .contentType(blogPostEntity.getContentType())
                .contentFileName(blogPostEntity.getContentFileName())
                .createdDateTime(blogPostEntity.getCreatedDateTime())
                .build();
    }

    public BlogPostEntity mapToBlogPostEntity(BlogPostDomainModel blogPostDomainModel) {
        if (null == blogPostDomainModel) {
            return null;
        }
        return BlogPostEntity
                .builder()
                .id(blogPostDomainModel.getId())
                .title(blogPostDomainModel.getTitle())
                .authorId(blogPostDomainModel.getAuthorId())
                .contentType(blogPostDomainModel.getContentType())
                .contentFileName(blogPostDomainModel.getContentFileName())
                .createdDateTime(blogPostDomainModel.getCreatedDateTime())
                .build();
    }
}
