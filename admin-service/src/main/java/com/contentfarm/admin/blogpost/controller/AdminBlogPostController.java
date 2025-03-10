package com.contentfarm.admin.blogpost.controller;

import com.contentfarm.admin.blogpost.service.IAdminBlogPostCommandService;
import com.contentfarm.command.blogpost.AdminAddBlogPostTagCommand;
import com.contentfarm.command.blogpost.AdminDeleteBlogPostCommand;
import com.contentfarm.command.blogpost.AdminDeleteBlogPostTagCommand;
import com.contentfarm.command.blogpost.AdminIndexBlogPostCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin/blogpost")
@RestController
public class AdminBlogPostController {
    private final IAdminBlogPostCommandService adminBlogPostCommandService;

    public AdminBlogPostController(IAdminBlogPostCommandService adminBlogPostCommandService) {
        this.adminBlogPostCommandService = adminBlogPostCommandService;
    }

    @PostMapping(path = "", produces = "application/json")
    public ResponseEntity<Void> addTagList(@RequestBody AdminIndexBlogPostCommand adminIndexBlogPostCommand) {
        adminBlogPostCommandService.indexBlogPost(adminIndexBlogPostCommand);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "", produces = "application/json")
    public ResponseEntity<Void> deleteTagList(@RequestBody AdminDeleteBlogPostCommand adminDeleteBlogPostCommand) {
        adminBlogPostCommandService.deleteBlogPost(adminDeleteBlogPostCommand);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/tag", produces = "application/json")
    public ResponseEntity<Void> addTagList(@RequestBody AdminAddBlogPostTagCommand adminAddBlogPostTagCommand) {
        adminBlogPostCommandService.addBlogPostTag(adminAddBlogPostTagCommand);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/tag", produces = "application/json")
    public ResponseEntity<Void> deleteTagList(@RequestBody AdminDeleteBlogPostTagCommand adminDeleteBlogPostTagCommand) {
        adminBlogPostCommandService.deleteBlogPostTag(adminDeleteBlogPostTagCommand);
        return ResponseEntity.noContent().build();
    }
}
