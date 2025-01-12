package com.contentfarm.persistence.dao;

import com.contentfarm.persistence.entity.blogpost.BlogPostEntity;
import com.querydsl.core.types.Predicate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@NoRepositoryBean
public interface BaseDao<E, ID> extends CrudRepository<E, ID>, PagingAndSortingRepository<E, ID>, JpaRepository<E, ID>, QuerydslPredicateExecutor<E> {
}
