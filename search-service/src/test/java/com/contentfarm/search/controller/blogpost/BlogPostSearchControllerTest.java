package com.contentfarm.search.controller.blogpost;

import com.contentfarm.dto.blogpost.BlogPostSearchResultDto;
import com.contentfarm.dto.common.SearchResult;
import com.contentfarm.search.TestElasticSearchContainer;
import com.contentfarm.search.document.blogpost.BlogPostDocument;
import com.contentfarm.search.repository.blogpost.BlogPostElasticsearchRepository;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.context.ImportTestcontainers;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ImportTestcontainers(TestElasticSearchContainer.class)
@SpringBootTest
@AutoConfigureMockMvc
class BlogPostSearchControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    BlogPostElasticsearchRepository blogPostElasticsearchRepository;

    @BeforeAll
    void setUp() {
        var testingDocument = findTestingBlogPostDocument();
        blogPostElasticsearchRepository.saveAll(testingDocument);
    }

    @Test
    void searchBlogPostByKeywordAndPageNumberAndPageSize() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/search/blogpost?keyword=keyword"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.searchResultCount").value(findTestingBlogPostDocument().size()));

        mockMvc.perform(MockMvcRequestBuilders.get("/search/blogpost?keyword=keyword&pageNumber=0&pageSize=10"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.searchResultItemList").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.searchResultItemList.length()").value(10));

        mockMvc.perform(MockMvcRequestBuilders.get("/search/blogpost?keyword=keyword&pageNumber=1&pageSize=10"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.searchResultCount").value(20))
                .andExpect(MockMvcResultMatchers.jsonPath("$.searchResultItemList.length()").value(10));

        mockMvc.perform(MockMvcRequestBuilders.get("/search/blogpost?keyword=keyword&pageNumber=2&pageSize=10"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.searchResultCount").value(20))
                .andExpect(MockMvcResultMatchers.jsonPath("$.searchResultItemList.length()").value(0));

        mockMvc.perform(MockMvcRequestBuilders.get("/search/blogpost?keyword=keyword&pageNumber=0&pageSize=9"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.searchResultItemList").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.searchResultItemList.length()").value(9));

        mockMvc.perform(MockMvcRequestBuilders.get("/search/blogpost?keyword=keyword&pageNumber=1&pageSize=9"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.searchResultCount").value(20))
                .andExpect(MockMvcResultMatchers.jsonPath("$.searchResultItemList.length()").value(9));

        mockMvc.perform(MockMvcRequestBuilders.get("/search/blogpost?keyword=keyword&pageNumber=2&pageSize=9"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.searchResultCount").value(20))
                .andExpect(MockMvcResultMatchers.jsonPath("$.searchResultItemList.length()").value(2));

        mockMvc.perform(MockMvcRequestBuilders.get("/search/blogpost?keyword=keyword&pageNumber=3&pageSize=9"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.searchResultCount").value(20))
                .andExpect(MockMvcResultMatchers.jsonPath("$.searchResultItemList.length()").value(0));

        mockMvc.perform(MockMvcRequestBuilders.get("/search/blogpost?keyword=testingSummary1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.searchResultCount").value(1));

        mockMvc.perform(MockMvcRequestBuilders.get("/search/blogpost?keyword=testingTitle1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.searchResultCount").value(1));

        mockMvc.perform(MockMvcRequestBuilders.get("/search/blogpost?keyword=testingSummary20"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.searchResultCount").value(1));

        mockMvc.perform(MockMvcRequestBuilders.get("/search/blogpost?keyword=testingTitle20"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.searchResultCount").value(1));

        mockMvc.perform(MockMvcRequestBuilders.get("/search/blogpost?keyword=invalidKeyword"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.searchResultCount").value(0));
    }

    @Test
    void searchBlogPostByTagList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/search/blogpost?tagList="))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.searchResultCount").value(findTestingBlogPostDocument().size()));

        mockMvc.perform(MockMvcRequestBuilders.get("/search/blogpost?tagList=Even"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.searchResultCount").value(10));

        mockMvc.perform(MockMvcRequestBuilders.get("/search/blogpost?tagList=Odd"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.searchResultCount").value(10));

        mockMvc.perform(MockMvcRequestBuilders.get("/search/blogpost?tagList=Invalid"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.searchResultCount").value(0));
    }

    @Test
    void getBlogPostById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/search/blogpost/testingId1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("testingId1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("testingTitle1"));
    }

    @Test
    void getBlogPostById_InvalidId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/search/blogpost/invalidId"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Document not found."));
    }

    @Test
    void searchAllBlogPost() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/search/blogpost"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.searchResultCount").value(findTestingBlogPostDocument().size()));
    }

    private List<BlogPostDocument> findTestingBlogPostDocument() {
        var result = new ArrayList<BlogPostDocument>();
        for (int i = 0; i < 20; i++) {
            var testingBlogPostDocument = buildNormalTestBlogPostDocument(i + 1);
            result.add(testingBlogPostDocument);
        }
        return result;
    }

    private BlogPostDocument buildNormalTestBlogPostDocument(int orderNumber) {
        return BlogPostDocument
                .builder()
                .id(MessageFormat.format("testingId{0}", orderNumber))
                .title(MessageFormat.format("testingTitle{0}", orderNumber))
                .tagList(orderNumber % 2 == 0 ? List.of("Even") : List.of("Odd"))
                .summary(MessageFormat.format("This is a testingSummary{0} keyword", orderNumber))
                .build();
    }
}