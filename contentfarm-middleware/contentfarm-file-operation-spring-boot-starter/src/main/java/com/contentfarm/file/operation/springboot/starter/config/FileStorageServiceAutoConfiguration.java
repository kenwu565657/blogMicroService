package com.contentfarm.file.operation.springboot.starter.config;

import com.contentfarm.file.operation.springboot.starter.service.FileStorageService;
import com.contentfarm.file.operation.springboot.starter.service.impl.AmazonS3FileStorageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileStorageServiceAutoConfiguration {
    @Bean
    public FileStorageService fileStorageService(AmazonS3Operation amazonS3Operation) {
        return new AmazonS3FileStorageService(amazonS3Operation);
    }
}
