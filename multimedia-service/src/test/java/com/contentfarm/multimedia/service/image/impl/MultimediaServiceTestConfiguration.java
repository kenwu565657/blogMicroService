package com.contentfarm.multimedia.service.image.impl;

import com.contentfarm.file.operation.springboot.starter.exception.FileOperationException;
import com.contentfarm.file.operation.springboot.starter.pojo.AsyncOperationResult;
import com.contentfarm.file.operation.springboot.starter.service.FileStorageService;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;

public class MultimediaServiceTestConfiguration {
    @Bean
    public FileStorageService fileStorageService() {
        return new FileStorageServiceSpy();
    }

    public static class FileStorageServiceSpy implements FileStorageService {
        private byte[] defaultFileByteArray;
        public static String TESTING_FILE_NAME = "image/java.png";
        public static String TESTING_DICTIONARY_NAME = "contentfarmblogpost";

        public FileStorageServiceSpy() {
            try {
                Path currentRelativePath = Paths.get("");
                String s = currentRelativePath.toAbsolutePath().toString();
                String TEST_FILE_CLASS_PATH = "/src/test/resources/java.png";
                defaultFileByteArray = Files.readAllBytes(Path.of(s + TEST_FILE_CLASS_PATH));
            } catch (
                    IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void createDirectory(String directoryName) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void deleteDirectory(String directoryName) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void uploadFile(String directoryName, String fileName, File file) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public byte[] downloadFile(String directoryName, String fileName) {
            if (TESTING_DICTIONARY_NAME.equals(directoryName) && TESTING_FILE_NAME.equals(fileName)) {
                return defaultFileByteArray;
            }
            return null;
        }

        @Override
        public CompletableFuture<AsyncOperationResult<byte[]>> downloadFileAsync(String directoryName, String fileName) {
            if (TESTING_DICTIONARY_NAME.equals(directoryName) && TESTING_FILE_NAME.equals(fileName)) {
                return CompletableFuture.completedFuture(AsyncOperationResult.ofSuccess(defaultFileByteArray));
            }
            if (TESTING_FILE_NAME.equals(fileName)) {
                return CompletableFuture.completedFuture(AsyncOperationResult.ofFailure(FileOperationException.ofDirectoryNotExist()));
            }
            return CompletableFuture.completedFuture(AsyncOperationResult.ofFailure(FileOperationException.ofFileNameNotExist()));
        }

        @Override
        public void deleteFile(String directoryName, String fileName) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
