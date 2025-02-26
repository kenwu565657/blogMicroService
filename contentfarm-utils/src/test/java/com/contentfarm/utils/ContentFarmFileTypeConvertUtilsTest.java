package com.contentfarm.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class ContentFarmFileTypeConvertUtilsTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContentFarmFileTypeConvertUtilsTest.class);

    @Test
    void markdownToHtml() {
        String markdownContent = null;
        try {
            Path currentRelativePath = Paths.get("");
            String s = currentRelativePath.toAbsolutePath().toString();
            markdownContent = Files.readString(Path.of(s + TEST_FILE_CLASS_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String htmlContent = ContentFarmFileTypeConvertUtils.markdownToHtml(markdownContent);
        Assertions.assertTrue(htmlContent.contains(HTML_HEADER_1));
        Assertions.assertTrue(htmlContent.contains(HTML_HEADER_2));
        Assertions.assertTrue(htmlContent.contains(HTML_PARAGRAPH));
    }

    private static final String TEST_FILE_CLASS_PATH = "/src/test/resources/testing.md";
    private static final String HTML_HEADER_1 = "<h1>Testing Header 1</h1>";
    private static final String HTML_HEADER_2 = "<h2>Testing Header 2</h2>";
    private static final String HTML_PARAGRAPH = "<p>Testing Content</p>";
}