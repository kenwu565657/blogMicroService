package com.contentfarm.auth.server.service;

import com.contentfarm.auth.server.bean.GenerateCodeRequest;

public interface GenerateCodeService {
    String generateCode(GenerateCodeRequest generateCodeRequest);
}
