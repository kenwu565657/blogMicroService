package com.contentfarm.blog.service.web.mapper.blogpost;

import com.contentfarm.blog.service.domain.aggregateroot.blogpost.BlogPostDomainModel;
import com.contentfarm.dto.blogpost.BlogPostSummaryDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BlogPostMapper {

    public List<BlogPostSummaryDto> toBlogPostSummaryDtoList(List<BlogPostDomainModel> blogPostDomainModel) {
        if (null == blogPostDomainModel || blogPostDomainModel.isEmpty()) {
            return List.of();
        }
        return blogPostDomainModel.stream().filter(Objects::nonNull).map(this::toBlogPostSummaryDto).collect(Collectors.toList());
    }

    public BlogPostSummaryDto toBlogPostSummaryDto(BlogPostDomainModel blogPostDomainModel) {
        if (null == blogPostDomainModel) {
            return null;
        }
        var blogPostSummaryDto = new BlogPostSummaryDto();
        blogPostSummaryDto.setId(blogPostDomainModel.getId());
        blogPostSummaryDto.setTitle(blogPostDomainModel.getTitle());
        blogPostSummaryDto.setContent(blogPostDomainModel.getContent());
        blogPostSummaryDto.setSummary(blogPostDomainModel.getSummary());
        blogPostSummaryDto.setTagList(Optional.ofNullable(blogPostDomainModel.getTagList()).orElse(List.of()));
        blogPostSummaryDto.setAuthorId(blogPostDomainModel.getAuthorId());
        blogPostSummaryDto.setCreatedDateTime(blogPostDomainModel.getCreatedDateTime());
        return blogPostSummaryDto;
    }
}
