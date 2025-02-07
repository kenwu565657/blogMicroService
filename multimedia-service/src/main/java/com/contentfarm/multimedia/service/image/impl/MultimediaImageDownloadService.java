package com.contentfarm.multimedia.service.image.impl;

import com.contentfarm.file.operation.springboot.starter.service.FileStorageService;
import com.contentfarm.multimedia.service.image.IMultimediaImageDownloadService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MultimediaImageDownloadService implements IMultimediaImageDownloadService {

    private final FileStorageService fileStorageService;

    @Override
    public byte[] download(String imageUrl) {
        return new byte[0];
    }

    @Override
    public byte[] download(String directory, String imageName) {
        return fileStorageService.downloadFile(directory, imageName);
    }
}
