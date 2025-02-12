package com.contentfarm.dto.common;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class SearchResult<T> {
    private int searchResultCount;
    private long searchExecutionTimeInMs;
    private float maxSearchScore;
    private List<T> searchResultList;
}
