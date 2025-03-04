package com.contentfarm.dto.common;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class SearchResult<T> {
    private Integer searchResultCount;
    private Long searchExecutionTimeInMs;
    private Float maxSearchScore;
    private List<T> searchResultItemList;
}
