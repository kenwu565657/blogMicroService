package com.contentfarm.persistence.searchcriteria;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class BlogPostSearchCriteria extends BaseSearchCriteria {
    private String id;
}
