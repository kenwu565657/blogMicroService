package com.contentfarm.feign.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Mono;

@FeignClient(contextId = "multimedia-service", name = "multimedia-service", path = "/multimedia")
public interface MultimediaServiceFeign {
    @ResponseBody
    @GetMapping(value = "/{fileName}", produces = "image/png+jpeg")
    Mono<byte[]> getMultimediaImageByFileName(@PathVariable String fileName);
}
