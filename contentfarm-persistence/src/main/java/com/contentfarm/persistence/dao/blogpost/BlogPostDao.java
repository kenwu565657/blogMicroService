package com.contentfarm.persistence.dao.blogpost;

import com.contentfarm.persistence.dao.BaseDao;
import com.contentfarm.persistence.entity.blogpost.BlogPostEntity;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogPostDao extends JpaRepository<BlogPostEntity, String>, JpaSpecificationExecutor<BlogPostEntity> {

    List<BlogPostEntity> findByTitleContainingAndTitle(String title, String content);

    List<BlogPostEntity> findByAuthorId(String authorId);

}
