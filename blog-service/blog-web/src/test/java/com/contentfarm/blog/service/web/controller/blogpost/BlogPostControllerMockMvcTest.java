package com.contentfarm.blog.service.web.controller.blogpost;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(classes = BlogPostControllerTestConfiguration.class)
@AutoConfigureMockMvc
public class BlogPostControllerMockMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void findBlogPostSummary() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/blogpost/list"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getBlogPostContentByBlogPostId() {
    }

    @Test
    void getBlogPostContentAsMarkdownFileByBlogPostId() {
    }

    @Test
    void findTagList() {
    }
}
