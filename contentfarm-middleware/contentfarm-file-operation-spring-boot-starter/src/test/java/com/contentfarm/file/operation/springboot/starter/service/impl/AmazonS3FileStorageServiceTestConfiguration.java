package com.contentfarm.file.operation.springboot.starter.service.impl;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication(scanBasePackages = {"com.contentfarm.file.operation.springboot.starter"})
public class AmazonS3FileStorageServiceTestConfiguration {
}
