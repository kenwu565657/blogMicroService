package com.contentfarm.file.operation.springboot.starter.service.impl;

import com.contentfarm.file.operation.springboot.starter.config.AmazonS3AutoConfiguration;
import com.contentfarm.file.operation.springboot.starter.config.AmazonS3Operation;
import com.contentfarm.file.operation.springboot.starter.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.File;

@Import(AmazonS3AutoConfiguration.class)
@Primary
@Component
public class AmazonS3FileStorageService implements FileStorageService {

    @Autowired(required = false)
    private AmazonS3Operation amazonS3Operation;

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
    public void deleteFile(String directoryName, String fileName) {
        amazonS3Operation.deleteFile(directoryName, fileName);
    }
}
