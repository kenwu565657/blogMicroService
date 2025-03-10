package com.contentfarm.command.blogpost;

import java.util.List;

public class AdminAddBlogPostTagCommand {
    private final List<String> blogPostTagList;

    public AdminAddBlogPostTagCommand(List<String> blogPostTagList) {
        this.blogPostTagList = blogPostTagList;
    }

    public List<String> getBlogPostTagList() {
        return blogPostTagList;
    }
}
