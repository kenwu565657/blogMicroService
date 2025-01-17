package com.contentfarm.file.operation.springboot.starter.service;

import java.io.File;

public interface FileStorageService {

    void createDirectory(String directoryName);

    void deleteDirectory(String directoryName);

    void uploadFile(String directoryName, String fileName, File file);

    byte[] downloadFile(String directoryName, String fileName);

    void deleteFile(String directoryName, String fileName);
}
