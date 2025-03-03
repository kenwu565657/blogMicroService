package com.contentfarm.file.operation.springboot.starter.config;

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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;
import software.amazon.awssdk.services.s3.model.Bucket;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = {AmazonS3OperatorTestConfiguration.class})
public class AmazonS3OperatorTest {

    private final Logger logger = LoggerFactory.getLogger(AmazonS3OperatorTest.class);

    @Autowired
    private AmazonS3Operation amazonS3Operator;

    private static final String TEST_TXT_FIlE_CLASS_PATH = "classpath:testingFile.txt";
    private static final String TEST_PNG_FIlE_CLASS_PATH = "classpath:testingFile.png";
    private static final String TEST_BUCKET_NAME_TEMPLATE = "my-test-bucket-{0}";
    private static final String TEST_FILE_NAME_TEMPLATE = "my-test-file-{0}";
    private static final String TEST_FILE_NAME_WITH_PREFIX_PATH_TEMPLATE = "my-prefix/my-test-file-{0}";
    private final String invalidBucketName = "invalidBucketName";
    private final String invalidFileName = "invalidFileName";
    private int numberOfBuckets = 0;
    private String testingBucketName = null;
    private String testingTxtFileName = null;
    private String testingTxtFileNameWithPrefixPath = null;
    private byte[] testingTxtFileContent = null;
    private String testingPngFileName = null;
    private String testingPngFileNameWithPrefixPath = null;
    private byte[] testingPngFileContent = null;

    @BeforeAll
    void setUp() {
        String randomNumber = UUID.randomUUID().toString();
        testingBucketName = MessageFormat.format(TEST_BUCKET_NAME_TEMPLATE, randomNumber);
        testingTxtFileName = MessageFormat.format(TEST_FILE_NAME_TEMPLATE, randomNumber);
        testingTxtFileNameWithPrefixPath = MessageFormat.format(TEST_FILE_NAME_WITH_PREFIX_PATH_TEMPLATE, randomNumber);
        try {
            File testingFile = ResourceUtils.getFile(TEST_TXT_FIlE_CLASS_PATH);
            testingTxtFileContent = Files.readAllBytes(testingFile.toPath());
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }

        String randomNumber2 = UUID.randomUUID().toString();
        testingPngFileName = MessageFormat.format(TEST_FILE_NAME_TEMPLATE, randomNumber2);
        testingPngFileNameWithPrefixPath = MessageFormat.format(TEST_FILE_NAME_WITH_PREFIX_PATH_TEMPLATE, randomNumber2);
        try {
            File testingFile = ResourceUtils.getFile(TEST_PNG_FIlE_CLASS_PATH);
            testingPngFileContent = Files.readAllBytes(testingFile.toPath());
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Order(1)
    @Test
    void testInitListBuckets() {
        var bucketList = amazonS3Operator.listBuckets();
        Assertions.assertNotNull(bucketList);
        numberOfBuckets = bucketList.size();
    }

    @Order(2)
    @Test
    void testCreateBucket() {
        Assertions.assertDoesNotThrow(() -> amazonS3Operator.createBucket(testingBucketName));
        numberOfBuckets++;
    }

    @Order(3)
    @Test
    void testGetBucket() {
        var bucket = amazonS3Operator.getBucket(testingBucketName);
        Assertions.assertTrue(bucket.isPresent());
    }

    @Order(4)
    @Test
    void testAgainListBuckets() {
        var bucketList = amazonS3Operator.listBuckets();
        List<String> bucketNames = bucketList.stream().map(Bucket::name).toList();
        Assertions.assertEquals(numberOfBuckets, bucketNames.size());
        Assertions.assertTrue(bucketNames.contains(testingBucketName));
    }

    @Order(5)
    @Test
    void testUploadTxtFile() {
        try {
            File testingFile = ResourceUtils.getFile(TEST_TXT_FIlE_CLASS_PATH);
            Assertions.assertDoesNotThrow(() -> amazonS3Operator.uploadFile(testingBucketName, testingTxtFileName, testingFile));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Order(5)
    @Test
    void testUploadPngFile() {
        try {
            File testingFile = ResourceUtils.getFile(TEST_PNG_FIlE_CLASS_PATH);
            Assertions.assertDoesNotThrow(() -> amazonS3Operator.uploadFile(testingBucketName, testingPngFileName, testingFile));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Order(6)
    @Test
    void testDownloadFile_invalidBucketName() {
        FileOperationException fileOperationException = Assertions.assertThrows(FileOperationException.class, () -> amazonS3Operator.downloadFile(invalidBucketName, testingTxtFileName));
        Assertions.assertEquals("File Directory Not Exist.", fileOperationException.getMessage());
    }

    @Order(6)
    @Test
    void testDownloadFile_invalidFileName() {
        FileOperationException fileOperationException = Assertions.assertThrows(FileOperationException.class, () -> amazonS3Operator.downloadFile(testingBucketName, invalidFileName));
        Assertions.assertEquals("File Name Not Exist.", fileOperationException.getMessage());
    }

    @Order(6)
    @Test
    void testDownloadTxtFile() {
        byte[] byteArray = amazonS3Operator.downloadFile(testingBucketName, testingTxtFileName);
        Assertions.assertNotNull(byteArray);
        Assertions.assertArrayEquals(testingTxtFileContent, byteArray);
    }

    @Order(6)
    @Test
    void testDownloadTxtFileAsync() {
        AsyncOperationResult<byte[]> asyncOperationResult = amazonS3Operator.downloadFileAsync(testingBucketName, testingTxtFileName).join();
        Assertions.assertTrue(asyncOperationResult.isSuccess());
        Assertions.assertNotNull(asyncOperationResult.getData());
        Assertions.assertNull(asyncOperationResult.getFailCause());
        Assertions.assertArrayEquals(testingTxtFileContent, asyncOperationResult.getData());
    }

    @Order(6)
    @Test
    void testDownloadFileAsync_invalidBucketName() {
        AsyncOperationResult<byte[]> asyncOperationResult = amazonS3Operator.downloadFileAsync(invalidBucketName, testingTxtFileName).join();
        Assertions.assertFalse(asyncOperationResult.isSuccess());
        Assertions.assertNotNull(asyncOperationResult.getFailCause());
        Assertions.assertNull(asyncOperationResult.getData());
        Assertions.assertInstanceOf(FileOperationException.class, asyncOperationResult.getFailCause());
        Assertions.assertEquals("File Directory Not Exist.", asyncOperationResult.getFailCause().getMessage());
    }

    @Order(6)
    @Test
    void testDownloadFileAsync_invalidFileName() {
        AsyncOperationResult<byte[]> asyncOperationResult = amazonS3Operator.downloadFileAsync(invalidBucketName, testingTxtFileName).join();
        Assertions.assertFalse(asyncOperationResult.isSuccess());
        Assertions.assertNotNull(asyncOperationResult.getFailCause());
        Assertions.assertNull(asyncOperationResult.getData());
        Assertions.assertInstanceOf(FileOperationException.class, asyncOperationResult.getFailCause());
        Assertions.assertEquals("File Directory Not Exist.", asyncOperationResult.getFailCause().getMessage());
    }

    @Order(6)
    @Test
    void testDownloadPngFile() {
        byte[] byteArray = amazonS3Operator.downloadFile(testingBucketName, testingPngFileName);
        Assertions.assertNotNull(byteArray);
        Assertions.assertArrayEquals(testingPngFileContent, byteArray);
    }

    @Order(6)
    @Test
    void testDownloadPngFileAsync() {
        var asyncResult = amazonS3Operator.downloadFileAsync(testingBucketName, testingPngFileName).join();
        Assertions.assertTrue(asyncResult.isSuccess());
        Assertions.assertNotNull(asyncResult.getData());
        Assertions.assertNull(asyncResult.getFailCause());
        Assertions.assertArrayEquals(testingPngFileContent, asyncResult.getData());
    }

    @Order(7)
    @Test
    void testDeleteFile() {
        Assertions.assertDoesNotThrow(() -> amazonS3Operator.deleteFile(testingBucketName, testingTxtFileName));
        Assertions.assertDoesNotThrow(() -> amazonS3Operator.deleteFile(testingBucketName, testingPngFileName));
    }

    @Order(8)
    @Test
    void testUploadTxtFileWithPrefixPath() {
        try {
            File testingFile = ResourceUtils.getFile(TEST_TXT_FIlE_CLASS_PATH);
            Assertions.assertDoesNotThrow(() -> amazonS3Operator.uploadFile(testingBucketName, testingTxtFileNameWithPrefixPath, testingFile));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Order(8)
    @Test
    void testUploadPngFileWithPrefixPath() {
        try {
            File testingFile = ResourceUtils.getFile(TEST_PNG_FIlE_CLASS_PATH);
            Assertions.assertDoesNotThrow(() -> amazonS3Operator.uploadFile(testingBucketName, testingPngFileNameWithPrefixPath, testingFile));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Order(9)
    @Test
    void testDownloadTxtFileWithPrefixPath() {
        byte[] byteArray = amazonS3Operator.downloadFile(testingBucketName, testingTxtFileNameWithPrefixPath);
        Assertions.assertNotNull(byteArray);
        Assertions.assertArrayEquals(testingTxtFileContent, byteArray);
    }

    @Order(9)
    @Test
    void testDownloadTxtFileAsyncWithPrefixPath() {
        AsyncOperationResult<byte[]> asyncOperationResult = amazonS3Operator.downloadFileAsync(testingBucketName, testingTxtFileNameWithPrefixPath).join();
        Assertions.assertTrue(asyncOperationResult.isSuccess());
        Assertions.assertNotNull(asyncOperationResult.getData());
        Assertions.assertNull(asyncOperationResult.getFailCause());
        Assertions.assertArrayEquals(testingTxtFileContent, asyncOperationResult.getData());
    }

    @Order(9)
    @Test
    void testDownloadPngFileWithPrefixPath() {
        byte[] byteArray = amazonS3Operator.downloadFile(testingBucketName, testingPngFileNameWithPrefixPath);
        Assertions.assertNotNull(byteArray);
        Assertions.assertArrayEquals(testingPngFileContent, byteArray);
    }

    @Order(9)
    @Test
    void testDownloadPngFileAsyncWithPrefixPath() {
        AsyncOperationResult<byte[]> asyncOperationResult = amazonS3Operator.downloadFileAsync(testingBucketName, testingPngFileNameWithPrefixPath).join();
        Assertions.assertTrue(asyncOperationResult.isSuccess());
        Assertions.assertNotNull(asyncOperationResult.getData());
        Assertions.assertNull(asyncOperationResult.getFailCause());
        Assertions.assertArrayEquals(testingPngFileContent, asyncOperationResult.getData());
    }

    @Order(10)
    @Test
    void testDeleteFileWithPrefixPath() {
        Assertions.assertDoesNotThrow(() -> amazonS3Operator.deleteFile(testingBucketName, testingTxtFileNameWithPrefixPath));
        Assertions.assertDoesNotThrow(() -> amazonS3Operator.deleteFile(testingBucketName, testingPngFileNameWithPrefixPath));
    }

    @Order(Integer.MAX_VALUE)
    @Test
    void testDeleteBucket() {
        Assertions.assertDoesNotThrow(() -> amazonS3Operator.deleteBucket(testingBucketName));
    }
}