package com.contentfarm.blog.service.persistence.entity.blogpost;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="BLOG_POST_TAG")
public class BlogPostTagEntity {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "TAG_NAME")
    private String tagName;
}
