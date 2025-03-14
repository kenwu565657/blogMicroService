package com.contentfarm.blog.service.persistence.dao.blogpost;

import com.contentfarm.blog.service.persistence.entity.blogpost.BlogPostTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Collection;
import java.util.List;

public interface BlogPostTagDao extends JpaRepository<BlogPostTagEntity, String>, JpaSpecificationExecutor<BlogPostTagEntity> {
    List<BlogPostTagEntity> findByTagNameIn(Collection<String> tagName);
}
