package com.contentfarm.search.document.blogpost;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import java.util.List;

@Data
@ToString
@Document(indexName = "blogpost", createIndex = false)
public class BlogPostDocument {
    @Id
    private String id;

    @Field(name = "title", type = FieldType.Text)
    private String title;

    @Field(name = "tagList", type = FieldType.Text)
    private List<String> tagList;

    @Field(name = "summary", type = FieldType.Text)
    private String summary;

    @Field(name = "authorName", type = FieldType.Text)
    private String authorName;

    @Field(name = "postDate", type = FieldType.Text)
    private String postDate;

    @Field(name = "imageUrl", type = FieldType.Text)
    private String imageUrl;
}
