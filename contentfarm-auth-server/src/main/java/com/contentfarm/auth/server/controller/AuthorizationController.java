package com.contentfarm.auth.server.controller;

import com.contentfarm.auth.server.bean.GenerateCodeRequest;
import com.contentfarm.auth.server.service.GenerateCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * source:
 * <p>https://gitee.com/ashegit/spring-authorization-server-demo/tree/1.0.0</p>
 * Copy from <a href="https://gitee.com/ashegit/spring-authorization-server-demo/tree/1.0.0">...</a>
 *
 */

@RestController("/authorization")
@RequiredArgsConstructor
public class AuthorizationController {
    private final GenerateCodeService generateCodeService;

    @PostMapping
    public String generateCode(@RequestBody GenerateCodeRequest generateCodeRequest) {
        return generateCodeService.generateCode(generateCodeRequest);
    }
}
