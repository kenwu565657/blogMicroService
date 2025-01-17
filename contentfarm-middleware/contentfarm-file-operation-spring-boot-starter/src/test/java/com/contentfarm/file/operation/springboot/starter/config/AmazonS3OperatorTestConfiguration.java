package com.contentfarm.file.operation.springboot.starter.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties
@SpringBootApplication(scanBasePackages = {"com.contentfarm.file.operation.springboot.starter"})
public class AmazonS3OperatorTestConfiguration {

    @Bean
    AmazonS3Properties amazonS3Properties() {
        return new AmazonS3Properties();
    }
}
