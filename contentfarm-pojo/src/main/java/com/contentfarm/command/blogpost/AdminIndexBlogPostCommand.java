package com.contentfarm.command.blogpost;

import com.contentfarm.constant.blogpost.BlogPostContentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class AdminIndexBlogPostCommand {
    private String id;

    @NotBlank
    private String title;

    private List<String> tagList;

    @NotBlank
    private String summary;

    @NotBlank
    private String authorName;

    @NotNull
    private LocalDateTime postDate;

    @NotBlank
    private String coverImageUrl;

    @NotBlank
    private String contentFileName;

    @NotNull
    private BlogPostContentType contentType;
}
