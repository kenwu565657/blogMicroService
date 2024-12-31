package com.contentfarm.persistence.mapper;

import com.contentfarm.contentfarmdomain.aggregateroot.blogpost.BlogPostDomainModel;
import com.contentfarm.persistence.entity.BlogPostEntity;
import org.springframework.stereotype.Component;

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

}
