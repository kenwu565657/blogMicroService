package com.contentfarm.domain.post;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PostDomainModel {
    private String id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;
    private List<String> tagList;
}
