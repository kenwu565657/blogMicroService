package com.contentfarm.persistence.dao;

import com.contentfarm.persistence.searchcriteria.BaseSearchCriteria;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Selection;
import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class BaseDao<E, ID> {
    private final JpaRepository<E, ID> jpaRepository;

    private final JpaSpecificationExecutor<E> jpaSpecificationExecutor;

    @Autowired
    private final SessionFactory sessionFactory;

    public Optional<E> getBySearchCriteria(BaseSearchCriteria baseSearchCriteria) {
        List<Predicate> predicates = new ArrayList<>();
        Specification specification = ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("id"), 1));
            query.select(Selection.)
            predicates.add(criteriaBuilder.)

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        });

        Pageable pageable = PageRequest.of(0, 10);
        Page<E> page = this.findAll(specification, pageable);
    }


}
