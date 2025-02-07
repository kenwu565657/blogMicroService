package com.contentfarm.utils;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

class ContentFarmJWTUtilsTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContentFarmJWTUtilsTest.class);

    @Test
    void generateJWTToken() {
        String jwtToken = ContentFarmJWTUtils.generateJWTToken("contentfarm");
        LOGGER.info(() -> jwtToken);
        Assertions.assertTrue(StringUtils.isNotBlank(jwtToken));
    }
}