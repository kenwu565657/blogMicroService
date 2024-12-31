package com.contentfarm.persistence.searchcriteria;

import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Pageable;

import java.util.List;

@SuperBuilder
public class BaseSearchCriteria {
    Integer pageNumber;
    Integer pageSize;
    List<String> orderBy;

    Pageable pageable;

}
