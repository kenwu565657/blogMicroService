package com.contentfarm.persistence.dao.blogpost;

import com.contentfarm.persistence.entity.blogpost.BlogPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

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
