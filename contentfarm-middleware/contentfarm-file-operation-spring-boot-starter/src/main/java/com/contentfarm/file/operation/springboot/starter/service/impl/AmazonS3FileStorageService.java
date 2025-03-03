package com.contentfarm.file.operation.springboot.starter.service.impl;

import com.contentfarm.file.operation.springboot.starter.config.AmazonS3AutoConfiguration;
import com.contentfarm.file.operation.springboot.starter.config.AmazonS3Operation;
import com.contentfarm.file.operation.springboot.starter.config.AmazonS3Properties;
import com.contentfarm.file.operation.springboot.starter.pojo.AsyncOperationResult;
import com.contentfarm.file.operation.springboot.starter.service.FileStorageService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.concurrent.CompletableFuture;

@EnableConfigurationProperties(AmazonS3Properties.class)
@Import(AmazonS3AutoConfiguration.class)
@Component("AmazonS3FileStorageService")
public class AmazonS3FileStorageService implements FileStorageService {

    private final AmazonS3Operation amazonS3Operation;

    public AmazonS3FileStorageService(AmazonS3Operation amazonS3Operation) {
        this.amazonS3Operation = amazonS3Operation;
    }

    @Override
    public void createDirectory(String directoryName) {
        amazonS3Operation.createBucket(directoryName);
    }

    @Override
    public void deleteDirectory(String directoryName) {
        amazonS3Operation.deleteBucket(directoryName);
    }

    @Override
    public void uploadFile(String directoryName, String fileName, File file) {
        amazonS3Operation.uploadFile(directoryName, fileName, file);
    }

    @Override
    public byte[] downloadFile(String directoryName, String fileName) {
        return amazonS3Operation.downloadFile(directoryName, fileName);
    }

    @Override
    public CompletableFuture<AsyncOperationResult<byte[]>> downloadFileAsync(String directoryName, String fileName) {
        return amazonS3Operation.downloadFileAsync(directoryName, fileName);
    }

    @Override
    public void deleteFile(String directoryName, String fileName) {
        amazonS3Operation.deleteFile(directoryName, fileName);
    }
}
