package com.contentfarm.blog.service.persistence.entity.blogpost;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

    @Basic(fetch = FetchType.LAZY)
    //@Lob
    @Column(name = "CONTENT")
    private String content;

    @Column(name = "AUTHOR_ID")
    private String authorId;

    //@Lob
    @Column(name = "SUMMARY")
    private String summary;

    @Column(name = "CREATED_DATE_TIME")
    private LocalDateTime createdDateTime;

    @Column(name = "LAST_UPDATED_DATE_TIME")
    private LocalDateTime lastUpdatedDateTime;

}
