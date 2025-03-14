package com.contentfarm.blog.service.domain.domainservice.tag;

import com.contentfarm.blog.service.domain.inputport.web.tag.IBlogPostTagWebDomainService;
import com.contentfarm.blog.service.domain.outputport.persistence.IBlogPostPersistenceCommandService;
import com.contentfarm.blog.service.domain.outputport.persistence.IBlogPostPersistenceQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogPostTagDomainService implements IBlogPostTagWebDomainService {
    private final IBlogPostPersistenceQueryService blogPostPersistenceQueryService;
    private final IBlogPostPersistenceCommandService blogPostPersistenceCommandService;

    @Override
    public List<String> findTagList() {
        return blogPostPersistenceQueryService.findTagList();
    }

    @Override
    public void addBlogPostTag(List<String> blogPostTagNameList) {
        blogPostPersistenceCommandService.addBlogPostTag(blogPostTagNameList);
    }

    @Override
    public void deleteBlogPostTag(List<String> blogPostTagNameList) {
        blogPostPersistenceCommandService.deleteBlogPostTag(blogPostTagNameList);
    }
}
