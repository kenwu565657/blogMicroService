package com.contentfarm.blog.service.web.mapper.blogpost;

import com.contentfarm.blog.service.domain.aggregateroot.blogpost.BlogPostDomainModel;
import com.contentfarm.dto.blogpost.BlogPostSummaryDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BlogPostMapper {

    public List<BlogPostSummaryDto> toBlogPostSummaryDtoList(List<BlogPostDomainModel> blogPostDomainModel) {
        if (blogPostDomainModel.isEmpty()) {
            return List.of();
        }
        return blogPostDomainModel.stream().map(this::toBlogPostSummaryDto).collect(Collectors.toList());
    }

    public BlogPostSummaryDto toBlogPostSummaryDto(BlogPostDomainModel blogPostDomainModel) {
        var blogPostSummaryDto = new BlogPostSummaryDto();
        if (null == blogPostDomainModel) {
            return blogPostSummaryDto;
        }
        blogPostSummaryDto.setId(blogPostDomainModel.getId());
        blogPostSummaryDto.setTitle(blogPostDomainModel.getTitle());
        blogPostSummaryDto.setContent(blogPostDomainModel.getContent());
        blogPostSummaryDto.setAuthorId(blogPostSummaryDto.getAuthorId());
        blogPostSummaryDto.setCreatedDateTime(blogPostSummaryDto.getCreatedDateTime());
        return blogPostSummaryDto;
    }
}
