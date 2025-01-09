package com.contentfarm.persistence.searchcriteria.blogpost;

import com.contentfarm.persistence.searchcriteria.BaseSearchCriteria;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class BlogPostSearchCriteria extends BaseSearchCriteria {
    private String id;
}
