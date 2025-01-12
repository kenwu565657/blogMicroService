package com.contentfarm.blog.service.domain.domainservice.blogpost;

import com.contentfarm.blog.service.domain.outputport.blogpost.IBlogPostPersistenceQueryService;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.contentfarm")
public class BlogTestConfiguration {

    @Bean
    public IBlogPostPersistenceQueryService blogPostPersistenceQueryService() {
        return Mockito.mock(IBlogPostPersistenceQueryService.class);
    }
}
