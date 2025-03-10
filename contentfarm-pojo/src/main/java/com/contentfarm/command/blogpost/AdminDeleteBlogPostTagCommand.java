package com.contentfarm.command.blogpost;

import java.util.List;

public class AdminDeleteBlogPostTagCommand {
    private final List<String> blogPostTagList;

    public AdminDeleteBlogPostTagCommand(List<String> blogPostTagList) {
        this.blogPostTagList = blogPostTagList;
    }

    public List<String> getBlogPostTagList() {
        return blogPostTagList;
    }
}
