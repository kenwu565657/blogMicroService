package com.contentfarm.admin.blogpost.service;

import com.contentfarm.command.blogpost.AdminAddBlogPostTagCommand;
import com.contentfarm.command.blogpost.AdminDeleteBlogPostCommand;
import com.contentfarm.command.blogpost.AdminDeleteBlogPostTagCommand;
import com.contentfarm.command.blogpost.AdminIndexBlogPostCommand;

public interface IAdminBlogPostCommandService {
    void indexBlogPost(AdminIndexBlogPostCommand adminIndexBlogPostCommand);

    void addBlogPostTag(AdminAddBlogPostTagCommand adminAddBlogPostTagCommand);

    void deleteBlogPostTag(AdminDeleteBlogPostTagCommand adminDeleteBlogPostTagCommand);

    void deleteBlogPost(AdminDeleteBlogPostCommand adminDeleteBlogPostCommand);
}
