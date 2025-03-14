package com.contentfarm.blog.service.domain.inputport.mq.blogpost;

import com.contentfarm.command.blogpost.AdminDeleteBlogPostCommand;
import com.contentfarm.command.blogpost.AdminIndexBlogPostCommand;

public interface IBlogPostMessageQueueListener {
    void listenAdminIndexBlogPostCommand(AdminIndexBlogPostCommand adminAddBlogPostTagCommand);
    void listenAdminDeleteBlogPostCommand(AdminDeleteBlogPostCommand adminDeleteBlogPostCommand);
}
