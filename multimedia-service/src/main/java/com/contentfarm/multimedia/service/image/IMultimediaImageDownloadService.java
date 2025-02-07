package com.contentfarm.multimedia.service.image;

public interface IMultimediaImageDownloadService {
    byte[] download(String imageUrl);
    byte[] download(String directory, String imageName);
}
