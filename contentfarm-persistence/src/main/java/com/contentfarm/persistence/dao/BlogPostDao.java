package com.contentfarm.persistence.dao;

import com.contentfarm.persistence.entity.BlogPostEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface BlogPostDao extends JpaRepository<BlogPostEntity, String>, JpaSpecificationExecutor<BlogPostEntity> {

    /*
    Specification specification = ((root, query, criteriaBuilder) -> {
       List<Predicate> predicates = new ArrayList<>();
       predicates.add(criteriaBuilder.equal(root.get("id"), 1));

       return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    });

    Pageable pageable = PageRequest.of(0, 10);
    Page<BlogPostEntity> page = this.findAll(specification, pageable);

     */
}
