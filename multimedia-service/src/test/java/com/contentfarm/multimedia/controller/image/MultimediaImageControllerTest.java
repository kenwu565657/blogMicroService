package com.contentfarm.multimedia.controller.image;

import com.contentfarm.multimedia.service.image.IMultimediaImageDownloadService;
import com.contentfarm.multimedia.service.image.impl.MultimediaImageDownloadService;
import com.contentfarm.multimedia.service.image.impl.MultimediaServiceTestConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.MessageFormat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Import({MultimediaImageControllerTest.TestingConfiguration.class})
@WebFluxTest(controllers = MultimediaImageController.class)
class MultimediaImageControllerTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private byte[] testingFileContent;

    public static class TestingConfiguration {
        @Bean
        public IMultimediaImageDownloadService multimediaImageDownloadService() {
            return new MultimediaImageDownloadService(new MultimediaServiceTestConfiguration.FileStorageServiceSpy());
        }
    }

    @Autowired
    private WebTestClient webTestClient;

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
    void getMultimediaImageByFileName() {
        String urlTemplate = "/multimedia/image/{0}";
        String validUrl = MessageFormat.format(urlTemplate, "java.png");

        EntityExchangeResult<byte[]> result = webTestClient.get().uri(validUrl)
                                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.IMAGE_PNG_VALUE + "+jpeg")
                .expectBody(byte[].class)
                .returnResult();

        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getResponseBody());
        Assertions.assertTrue(result.getResponseBody().length > 0);
        Assertions.assertArrayEquals(testingFileContent, result.getResponseBody());
    }

    @Test
    void getMultimediaImageByFileName_invalidName() {
        String urlTemplate = "/multimedia/image/{0}";
        String validUrl = MessageFormat.format(urlTemplate, "invalidName.png");

        EntityExchangeResult<String> result = webTestClient.get().uri(validUrl)
                .exchange()
                .expectStatus().isBadRequest()
                .expectHeader().contentType(MediaType.TEXT_PLAIN_VALUE + ";charset=utf-8")
                .expectBody(String.class)
                .returnResult();

        Assertions.assertEquals("File Name Not Exist.", result.getResponseBody());
    }
}
