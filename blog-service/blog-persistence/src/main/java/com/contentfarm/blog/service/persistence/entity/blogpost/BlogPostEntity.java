package com.contentfarm.blog.service.persistence.entity.blogpost;

import com.contentfarm.constant.blogpost.BlogPostContentType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="BLOG_POST")
public class BlogPostEntity {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "AUTHOR_ID")
    private String authorId;

    @Enumerated(EnumType.STRING)
    @Column(name = "CONTENT_TYPE")
    private BlogPostContentType contentType;

    @Column(name = "CONTENT_FILE_NAME")
    private String contentFileName;

    @Column(name = "CREATED_DATE_TIME")
    private LocalDateTime createdDateTime;

    @Column(name = "LAST_UPDATED_DATE_TIME")
    private LocalDateTime lastUpdatedDateTime;
}
