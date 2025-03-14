package com.contentfarm.blog.service.persistence.service.blogpost;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication(scanBasePackages = "com.contentfarm.blog.service.persistence")
@EntityScan(basePackages = {"com.contentfarm.blog.service", "com.contentfarm.file.operation"})
public class BlogPostPersistenceQueryServiceTestConfiguration {
}
