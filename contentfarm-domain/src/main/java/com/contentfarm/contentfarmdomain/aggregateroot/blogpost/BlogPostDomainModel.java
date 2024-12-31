package com.contentfarm.contentfarmdomain.aggregateroot.blogpost;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BlogPostDomainModel {
    private String id;
    private String title;
    private String content;
    private LocalDateTime createdDateTime;
}
