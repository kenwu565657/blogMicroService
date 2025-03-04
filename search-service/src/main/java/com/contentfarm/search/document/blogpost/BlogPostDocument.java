package com.contentfarm.search.document.blogpost;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import java.util.List;

@Data
@ToString
@Document(indexName = "blogpost")
@FieldNameConstants
@Builder
public class BlogPostDocument {
    @Id
    private String id;

    @Field(name = "title", type = FieldType.Text)
    private String title;

    @Field(name = "tagList", type = FieldType.Keyword)
    private List<String> tagList;

    @Field(name = "summary", type = FieldType.Text)
    private String summary;

    @Field(name = "authorName", type = FieldType.Text, index = false)
    private String authorName;

    @Field(name = "postDate", type = FieldType.Text, index = false)
    private String postDate;

    @Field(name = "imageUrl", type = FieldType.Text, index = false)
    private String imageUrl;
}
