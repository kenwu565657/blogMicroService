package com.contentfarm.file.operation.springboot.starter.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.UUID;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@EnableConfigurationProperties
@SpringBootTest(classes = {AmazonS3FileStorageServiceTestConfiguration.class})
class AmazonS3FileStorageServiceTest {

    private final Logger logger = LoggerFactory.getLogger(AmazonS3FileStorageServiceTest.class);

    @Autowired
    @Qualifier("AmazonS3FileStorageService")
    private AmazonS3FileStorageService amazonS3FileStorageService;

    @BeforeAll
    void setUp() {
        String randomNumber = UUID.randomUUID().toString();
        testingDirectoryName = MessageFormat.format(TEST_DIRECTORY_NAME_TEMPLATE, randomNumber);
        testingFileName = MessageFormat.format(TEST_FILE_NAME_TEMPLATE, randomNumber);
        testingFileNameWithPrefix = MessageFormat.format(TEST_FILE_NAME_WITH_PREFIX_TEMPLATE, randomNumber);
    }

    @Order(1)
    @Test
    void createDirectory() {
        Assertions.assertDoesNotThrow(() -> amazonS3FileStorageService.createDirectory(testingDirectoryName));
    }

    @Order(2)
    @Test
    void uploadFile() {
        try {
            File testingFile = ResourceUtils.getFile(TEST_FIlE_CLASS_PATH);
            Assertions.assertDoesNotThrow(() -> amazonS3FileStorageService.uploadFile(testingDirectoryName, testingFileName, testingFile));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Order(3)
    @Test
    void downloadFile() {
        byte[] byteArray = amazonS3FileStorageService.downloadFile(testingDirectoryName, testingFileName);
        Assertions.assertNotNull(byteArray);
    }

    @Order(4)
    @Test
    void deleteFile() {
        Assertions.assertDoesNotThrow(() -> amazonS3FileStorageService.deleteFile(testingDirectoryName, testingFileName));
    }

    @Order(5)
    @Test
    void uploadFileWithPrefix() {
        try {
            File testingFile = ResourceUtils.getFile(TEST_FIlE_CLASS_PATH);
            Assertions.assertDoesNotThrow(() -> amazonS3FileStorageService.uploadFile(testingDirectoryName, testingFileNameWithPrefix, testingFile));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Order(6)
    @Test
    void downloadFileWithPrefix() {
        byte[] byteArray = amazonS3FileStorageService.downloadFile(testingDirectoryName, testingFileNameWithPrefix);
        Assertions.assertNotNull(byteArray);
    }

    @Order(7)
    @Test
    void deleteFileWithPrefix() {
        Assertions.assertDoesNotThrow(() -> amazonS3FileStorageService.deleteFile(testingDirectoryName, testingFileNameWithPrefix));
    }

    @Order(Integer.MAX_VALUE)
    @Test
    void deleteDirectory() {
        Assertions.assertDoesNotThrow(() -> amazonS3FileStorageService.deleteDirectory(testingDirectoryName));
    }

    private static final String TEST_FIlE_CLASS_PATH = "classpath:testingFile.txt";
    private static final String TEST_DIRECTORY_NAME_TEMPLATE = "my-test-directory-{0}";
    private static final String TEST_FILE_NAME_TEMPLATE = "my-test-file-{0}";
    private static final String TEST_FILE_NAME_WITH_PREFIX_TEMPLATE = "my-prefix/my-test-file-{0}";
    private String testingDirectoryName = null;
    private String testingFileName = null;
    private String testingFileNameWithPrefix = null;
}