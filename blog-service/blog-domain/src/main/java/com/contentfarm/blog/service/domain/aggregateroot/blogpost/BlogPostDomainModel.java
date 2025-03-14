package com.contentfarm.blog.service.domain.aggregateroot.blogpost;

import com.contentfarm.constant.blogpost.BlogPostContentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogPostDomainModel {
    private String id;
    private String title;
    private String summary;
    private String content;
    private String authorId;
    private List<String> tagList;
    private String coverImageUrl;
    private BlogPostContentType contentType;
    private String contentFileName;
    private LocalDateTime createdDateTime;
}
