package com.contentfarm.blog.service.persistence.dao.blogpost;

import com.contentfarm.file.operation.springboot.starter.config.AmazonS3Operation;
import com.contentfarm.file.operation.springboot.starter.config.AmazonS3Operator;
import com.contentfarm.file.operation.springboot.starter.config.AmazonS3Properties;
import com.contentfarm.file.operation.springboot.starter.service.FileStorageService;
import com.contentfarm.file.operation.springboot.starter.service.impl.AmazonS3FileStorageService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication(scanBasePackages = "com.contentfarm.blog.service.persistence")
public class BlogPostDaoTestConfiguration {
}
