package com.contentfarm.utils;

public class ContentFarmStringUtils {
    public static boolean isBlank(String value) {
        return null == value || "".equals(value);
    }
}
