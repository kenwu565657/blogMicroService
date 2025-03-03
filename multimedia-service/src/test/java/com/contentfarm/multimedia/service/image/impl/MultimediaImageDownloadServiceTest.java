package com.contentfarm.multimedia.service.image.impl;

import com.contentfarm.file.operation.springboot.starter.exception.FileOperationException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.util.ResourceUtils;
import reactor.test.StepVerifier;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Import({MultimediaServiceTestConfiguration.class})
@SpringBootTest
class MultimediaImageDownloadServiceTest {

    private final Logger logger = LoggerFactory.getLogger(MultimediaImageDownloadServiceTest.class);
    private byte[] testingFileContent;

    @Autowired
    MultimediaImageDownloadService multimediaImageDownloadService;

    @BeforeAll
    void setUp() {
        String TEST_FIlE_CLASS_PATH = "classpath:java.png";
        try {
            File testingFile = ResourceUtils.getFile(TEST_FIlE_CLASS_PATH);
            testingFileContent = Files.readAllBytes(testingFile.toPath());
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Test
    void downloadAsync() {
        var fileContent = multimediaImageDownloadService.downloadAsync(MultimediaServiceTestConfiguration.FileStorageServiceSpy.TESTING_DICTIONARY_NAME, MultimediaServiceTestConfiguration.FileStorageServiceSpy.TESTING_FILE_NAME);
        StepVerifier.create(fileContent)
                .expectNextMatches(fileContentByte -> Arrays.equals(testingFileContent, fileContentByte))
                .expectComplete()
                .verify();
    }

    @Test
    void downloadAsync_invalidFileNameAndInvalidFileExtension() {
        var fileContent = multimediaImageDownloadService.downloadAsync(MultimediaServiceTestConfiguration.FileStorageServiceSpy.TESTING_DICTIONARY_NAME, "java.pdf");
        StepVerifier.create(fileContent)
                .expectErrorMatches(throwable -> {
                    if (throwable instanceof FileOperationException) {
                        return "File Name Not Exist.".equals(throwable.getMessage());
                    }
                    return false;
                })
                .verify();
    }

    @Test
    void downloadAsync_invalidFileName() {
        var fileContent = multimediaImageDownloadService.downloadAsync(MultimediaServiceTestConfiguration.FileStorageServiceSpy.TESTING_DICTIONARY_NAME, "invalidName");
        StepVerifier.create(fileContent)
                .expectErrorMatches(throwable -> {
                    if (throwable instanceof FileOperationException) {
                        return "File Name Not Exist.".equals(throwable.getMessage());
                    }
                    return false;
                })
                .verify();
    }

    @Test
    void downloadAsync_invalidDirectoryName() {
        var fileContent = multimediaImageDownloadService.downloadAsync("invalidName", MultimediaServiceTestConfiguration.FileStorageServiceSpy.TESTING_FILE_NAME);
        StepVerifier.create(fileContent)
                .expectErrorMatches(throwable -> {
                    if (throwable instanceof FileOperationException) {
                        return "File Directory Not Exist.".equals(throwable.getMessage());
                    }
                    return false;
                })
                .verify();
    }

    @Test
    void downloadAsync_invalidFileNameAndInvalidFileName() {
        var fileContent = multimediaImageDownloadService.downloadAsync("invalidName", "invalidName");
        StepVerifier.create(fileContent)
                .expectErrorMatches(throwable -> {
                    if (throwable instanceof FileOperationException) {
                        return "File Name Not Exist.".equals(throwable.getMessage());
                    }
                    return false;
                })
                .verify();
    }
}
