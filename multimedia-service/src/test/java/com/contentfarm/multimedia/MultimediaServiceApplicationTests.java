package com.contentfarm.multimedia;

import com.contentfarm.file.operation.springboot.starter.service.FileStorageService;
import com.contentfarm.file.operation.springboot.starter.service.impl.AmazonS3FileStorageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class MultimediaServiceApplicationTests {
    @Autowired
    ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        Object fileStorageService = applicationContext.getBean("fileStorageService");
        Assertions.assertNotNull(fileStorageService);
        Assertions.assertInstanceOf(FileStorageService.class, fileStorageService);
        Assertions.assertInstanceOf(AmazonS3FileStorageService.class, fileStorageService);
    }

}
