package com.contentfarm.persistence.entity.blogpost;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "AUTHOR_ID")
    private String authorId;

    @Column(name = "SUMMARY")
    private String summary;

    @Column(name = "CREATE_DATE_TIME")
    private LocalDateTime createDateTime;

}
