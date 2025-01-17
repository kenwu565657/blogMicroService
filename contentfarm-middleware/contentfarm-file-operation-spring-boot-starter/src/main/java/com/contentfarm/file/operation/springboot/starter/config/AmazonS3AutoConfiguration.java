package com.contentfarm.file.operation.springboot.starter.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(AmazonS3Properties.class)
@Configuration
public class AmazonS3AutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public AmazonS3Operation amazonS3Operation(AmazonS3Properties amazonS3Properties) {
        return new AmazonS3Operator(amazonS3Properties);
    }
}
