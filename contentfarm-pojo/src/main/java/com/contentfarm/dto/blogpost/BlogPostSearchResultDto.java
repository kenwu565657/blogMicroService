package com.contentfarm.dto.blogpost;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class BlogPostSearchResultDto {
    private String id;
    private String title;
    private List<String> tagList;
    private String summary;
    private String authorName;
    private String postDate;
    private String imageUrl;
}
