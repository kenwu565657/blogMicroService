package com.contentfarm.blog.service.persistence.dao.blogpost;

import com.contentfarm.blog.service.persistence.entity.blogpost.BlogPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogPostDao extends JpaRepository<BlogPostEntity, String>, JpaSpecificationExecutor<BlogPostEntity> {
    List<BlogPostEntity> findByAuthorId(String authorId);
    ContentFileNameProjection getContentFileNameById(String id);

    interface ContentFileNameProjection {
        String getContentFileName();
    }
}