package com.contentfarm.blog.service.domain.inputport.web.tag;

import java.util.List;

public interface IBlogPostTagWebDomainService {
    List<String> findTagList();
    void addBlogPostTag(List<String> blogPostTagNameList);
    void deleteBlogPostTag(List<String> blogPostTagNameList);
}
