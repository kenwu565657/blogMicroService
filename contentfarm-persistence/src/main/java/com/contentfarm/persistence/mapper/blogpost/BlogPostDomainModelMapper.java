package com.contentfarm.persistence.mapper.blogpost;

import com.contentfarm.contentfarmdomain.aggregateroot.blogpost.BlogPostDomainModel;
import com.contentfarm.persistence.entity.blogpost.BlogPostEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BlogPostDomainModelMapper {

    public BlogPostDomainModel mapToBlogPostDomainModel(BlogPostEntity blogPostEntity) {
        if (null == blogPostEntity) {
            return null;
        }
        return BlogPostDomainModel
                .builder()
                .id(blogPostEntity.getId())
                .title(blogPostEntity.getTitle())
                .content(blogPostEntity.getContent())
                .build();
    }

    public List<BlogPostDomainModel> mapToBlogPostDomainModels(List<BlogPostEntity> blogPostEntityList) {
        if (blogPostEntityList == null || blogPostEntityList.isEmpty()) {
            return List.of();
        }
        return blogPostEntityList.stream().parallel().map(this::mapToBlogPostDomainModel).collect(Collectors.toList());
    }

}
