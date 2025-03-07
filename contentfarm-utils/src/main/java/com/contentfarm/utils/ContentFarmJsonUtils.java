package com.contentfarm.utils;

import com.contentfarm.utils.exception.ContentFarmJsonProcessingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ContentFarmJsonUtils {
    private ContentFarmJsonUtils() {}

    public static ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

    public static String serializeToJsonString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw ContentFarmJsonProcessingException.of(e.getMessage());
        }
    }

    public static <T> T deserializeFromJsonString(String jsonString, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonString, clazz);
        } catch (JsonProcessingException | IllegalArgumentException e) {
            throw ContentFarmJsonProcessingException.of(e.getMessage());
        }
    }
}
