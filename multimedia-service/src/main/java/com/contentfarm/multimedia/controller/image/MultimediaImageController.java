package com.contentfarm.multimedia.controller.image;

import com.contentfarm.feign.feign.MultimediaServiceFeign;
import com.contentfarm.multimedia.service.image.IMultimediaImageDownloadService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/multimedia/image")
@AllArgsConstructor
public class MultimediaImageController implements MultimediaServiceFeign {

    private final IMultimediaImageDownloadService multimediaImageDownloadService;

    @Override
    @GetMapping(value = "/{fileName}", produces = "image/png+jpeg")
    public Mono<byte[]> getMultimediaImageByFileName(@PathVariable String fileName) {
        return multimediaImageDownloadService.downloadAsync("contentfarmblogpost", "image/" + fileName);
    }
}
