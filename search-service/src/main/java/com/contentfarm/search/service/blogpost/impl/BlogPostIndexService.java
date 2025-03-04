package com.contentfarm.search.service.blogpost.impl;

import com.contentfarm.search.document.blogpost.BlogPostDocument;
import com.contentfarm.search.exception.DocumentIndexException;
import com.contentfarm.search.repository.blogpost.BlogPostElasticsearchRepository;
import com.contentfarm.search.service.blogpost.IBlogPostIndexService;
import com.contentfarm.utils.ContentFarmStringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BlogPostIndexService implements IBlogPostIndexService {
    private final BlogPostElasticsearchRepository blogPostElasticsearchRepository;

    public BlogPostIndexService(BlogPostElasticsearchRepository blogPostElasticsearchRepository) {
        this.blogPostElasticsearchRepository = blogPostElasticsearchRepository;
    }

    @Override
    public void addDocument(BlogPostDocument blogPostDocument) {
        if (null == blogPostDocument) {
            throw DocumentIndexException.ofNullDocument();
        }
        List<String> missingFieldList = findMissingFieldList(blogPostDocument);
        if (!missingFieldList.isEmpty()) {
            throw DocumentIndexException.ofMissingField(missingFieldList);
        }
        if (null == blogPostDocument.getTagList()) {
            blogPostDocument.setTagList(List.of());
        }
        try {
            blogPostElasticsearchRepository.save(blogPostDocument);
        } catch (Exception e) {
            throw DocumentIndexException.ofUnExpectedException(e);
        }
    }

    private List<String> findMissingFieldList(BlogPostDocument blogPostDocument) {
        Map<String, String> fieldNamingMap = Map.of(
                BlogPostDocument.Fields.id, "Id",
                BlogPostDocument.Fields.title, "Title",
                BlogPostDocument.Fields.summary, "Summary",
                BlogPostDocument.Fields.authorName, "Author Name",
                BlogPostDocument.Fields.postDate, "Post Date",
                BlogPostDocument.Fields.imageUrl, "Image Url"
        );
        List<String> missingFields = new ArrayList<>();
        if (ContentFarmStringUtils.isBlank(blogPostDocument.getId())) {
            missingFields.add(fieldNamingMap.get(BlogPostDocument.Fields.id));
        }
        if (ContentFarmStringUtils.isBlank(blogPostDocument.getTitle())) {
            missingFields.add(fieldNamingMap.get(BlogPostDocument.Fields.title));
        }
        if (ContentFarmStringUtils.isBlank(blogPostDocument.getSummary())) {
            missingFields.add(fieldNamingMap.get(BlogPostDocument.Fields.summary));
        }
        if (ContentFarmStringUtils.isBlank(blogPostDocument.getAuthorName())) {
            missingFields.add(fieldNamingMap.get(BlogPostDocument.Fields.authorName));
        }
        if (ContentFarmStringUtils.isBlank(blogPostDocument.getPostDate())) {
            missingFields.add(fieldNamingMap.get(BlogPostDocument.Fields.postDate));
        }
        if (ContentFarmStringUtils.isBlank(blogPostDocument.getImageUrl())) {
            missingFields.add(fieldNamingMap.get(BlogPostDocument.Fields.imageUrl));
        }
        return missingFields;
    }
}
