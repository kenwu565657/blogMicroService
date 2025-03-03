package com.contentfarm.file.operation.springboot.starter.service.impl;

import com.contentfarm.file.operation.springboot.starter.exception.FileOperationException;
import com.contentfarm.file.operation.springboot.starter.pojo.AsyncOperationResult;
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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.MessageFormat;
import java.util.UUID;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
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

        try {
            File testingFile = ResourceUtils.getFile(TEST_FIlE_CLASS_PATH);
            testingTxtFileContent = Files.readAllBytes(testingFile.toPath());
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
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
        Assertions.assertArrayEquals(testingTxtFileContent, byteArray);
    }

    @Order(3)
    @Test
    void downloadFileAsync() {
        var asyncResult = amazonS3FileStorageService.downloadFileAsync(testingDirectoryName, testingFileName).join();
        Assertions.assertNotNull(asyncResult.getData());
        Assertions.assertArrayEquals(testingTxtFileContent, asyncResult.getData());
    }

    @Order(3)
    @Test
    void downloadFileAsync_invalidBucketName() {
        AsyncOperationResult<byte[]> asyncOperationResult = amazonS3FileStorageService.downloadFileAsync("invalidName", testingFileName).join();
        Assertions.assertFalse(asyncOperationResult.isSuccess());
        Assertions.assertNotNull(asyncOperationResult.getFailCause());
        Assertions.assertNull(asyncOperationResult.getData());
        Assertions.assertInstanceOf(FileOperationException.class, asyncOperationResult.getFailCause());
        Assertions.assertEquals("File Directory Not Exist.", asyncOperationResult.getFailCause().getMessage());
    }

    @Order(3)
    @Test
    void downloadFileAsync_invalidFileName() {
        AsyncOperationResult<byte[]> asyncOperationResult = amazonS3FileStorageService.downloadFileAsync(testingDirectoryName, "invalidName").join();
        Assertions.assertFalse(asyncOperationResult.isSuccess());
        Assertions.assertNotNull(asyncOperationResult.getFailCause());
        Assertions.assertNull(asyncOperationResult.getData());
        Assertions.assertInstanceOf(FileOperationException.class, asyncOperationResult.getFailCause());
        Assertions.assertEquals("File Name Not Exist.", asyncOperationResult.getFailCause().getMessage());
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
        Assertions.assertArrayEquals(testingTxtFileContent, byteArray);
    }

    @Order(6)
    @Test
    void downloadFileWithPrefixAsync() {
        AsyncOperationResult<byte[]> asyncOperationResult = amazonS3FileStorageService.downloadFileAsync(testingDirectoryName, testingFileNameWithPrefix).join();
        Assertions.assertTrue(asyncOperationResult.isSuccess());
        Assertions.assertNotNull(asyncOperationResult.getData());
        Assertions.assertNull(asyncOperationResult.getFailCause());
        Assertions.assertArrayEquals(testingTxtFileContent, asyncOperationResult.getData());
    }

    @Order(6)
    @Test
    void downloadFileWithPrefixAsync_invalidBucketName() {
        AsyncOperationResult<byte[]> asyncOperationResult = amazonS3FileStorageService.downloadFileAsync("invalidName", testingFileNameWithPrefix).join();
        Assertions.assertFalse(asyncOperationResult.isSuccess());
        Assertions.assertNotNull(asyncOperationResult.getFailCause());
        Assertions.assertNull(asyncOperationResult.getData());
        Assertions.assertInstanceOf(FileOperationException.class, asyncOperationResult.getFailCause());
        Assertions.assertEquals("File Directory Not Exist.", asyncOperationResult.getFailCause().getMessage());
    }

    @Order(6)
    @Test
    void downloadFileWithPrefixAsync_invalidFileName() {
        AsyncOperationResult<byte[]> asyncOperationResult = amazonS3FileStorageService.downloadFileAsync(testingDirectoryName, "invalidName").join();
        Assertions.assertFalse(asyncOperationResult.isSuccess());
        Assertions.assertNotNull(asyncOperationResult.getFailCause());
        Assertions.assertNull(asyncOperationResult.getData());
        Assertions.assertInstanceOf(FileOperationException.class, asyncOperationResult.getFailCause());
        Assertions.assertEquals("File Name Not Exist.", asyncOperationResult.getFailCause().getMessage());
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
    private byte[] testingTxtFileContent = null;
}