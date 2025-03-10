package com.contentfarm.admin.blogpost.service.impl;

import com.contentfarm.admin.blogpost.service.IAdminBlogPostCommandService;
import com.contentfarm.admin.blogpost.service.saga.AdminDeleteBlogPostSaga;
import com.contentfarm.admin.blogpost.service.saga.AdminIndexBlogPostSaga;
import com.contentfarm.command.blogpost.AdminAddBlogPostTagCommand;
import com.contentfarm.command.blogpost.AdminDeleteBlogPostCommand;
import com.contentfarm.command.blogpost.AdminDeleteBlogPostTagCommand;
import com.contentfarm.command.blogpost.AdminIndexBlogPostCommand;
import com.contentfarm.feign.feign.BlogPostServiceFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminBlogPostCommandService implements IAdminBlogPostCommandService {

    private final BlogPostServiceFeign blogPostServiceFeign;
    private final AdminIndexBlogPostSaga adminIndexBlogPostSaga;
    private final AdminDeleteBlogPostSaga adminDeleteBlogPostSaga;

    @Override
    public void indexBlogPost(AdminIndexBlogPostCommand adminIndexBlogPostCommand) {
        adminIndexBlogPostSaga.indexBlogPost(adminIndexBlogPostCommand);
    }

    @Override
    public void deleteBlogPost(AdminDeleteBlogPostCommand adminDeleteBlogPostCommand) {
        adminDeleteBlogPostSaga.deleteBlogPostIndex(adminDeleteBlogPostCommand);
    }

    @Override
    public void addBlogPostTag(AdminAddBlogPostTagCommand adminAddBlogPostTagCommand) {
        blogPostServiceFeign.addTagList(adminAddBlogPostTagCommand.getBlogPostTagList());
    }

    @Override
    public void deleteBlogPostTag(AdminDeleteBlogPostTagCommand adminDeleteBlogPostTagCommand) {
        blogPostServiceFeign.deleteTagList(adminDeleteBlogPostTagCommand.getBlogPostTagList());
    }
}
