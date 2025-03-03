package com.contentfarm.file.operation.springboot.starter.service;

import com.contentfarm.file.operation.springboot.starter.pojo.AsyncOperationResult;

import java.io.File;
import java.util.concurrent.CompletableFuture;

public interface FileStorageService {

    void createDirectory(String directoryName);

    void deleteDirectory(String directoryName);

    void uploadFile(String directoryName, String fileName, File file);

    byte[] downloadFile(String directoryName, String fileName);

    CompletableFuture<AsyncOperationResult<byte[]>> downloadFileAsync(String directoryName, String fileName);

    void deleteFile(String directoryName, String fileName);
}
