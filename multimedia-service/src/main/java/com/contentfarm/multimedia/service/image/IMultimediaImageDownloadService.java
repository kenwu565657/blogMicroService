package com.contentfarm.multimedia.service.image;

import reactor.core.publisher.Mono;

public interface IMultimediaImageDownloadService {
    Mono<byte[]> downloadAsync(String directory, String imageName);
}
