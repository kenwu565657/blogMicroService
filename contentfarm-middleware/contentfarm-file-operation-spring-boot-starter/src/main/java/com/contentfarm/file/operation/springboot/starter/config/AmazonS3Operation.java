package com.contentfarm.file.operation.springboot.starter.config;

import software.amazon.awssdk.services.s3.model.Bucket;

import java.io.File;
import java.util.List;
import java.util.Optional;

public interface AmazonS3Operation {
    List<Bucket> listBuckets();

    Optional<Bucket> getBucket(String bucketName);

    void createBucket(String bucketName);

    void deleteBucket(String bucketName);

    void uploadFile(String bucketName, String fileName, File file);

    byte[] downloadFile(String bucketName, String fileName);

    void deleteFile(String bucketName, String fileName);
}
