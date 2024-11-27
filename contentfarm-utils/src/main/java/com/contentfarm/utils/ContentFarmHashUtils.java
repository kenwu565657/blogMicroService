package com.contentfarm.utils;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

public class ContentFarmHashUtils {
    public static String hash(String password) {
        Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(32,64,1,15*1024,2);
        return encoder.encode(password);
    }

    public static boolean match(String rawPassword, String encodedPassword) {
        if (ContentFarmStringUtils.isBlank(rawPassword) || ContentFarmStringUtils.isBlank(encodedPassword)) {
            return false;
        }
        Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(32,64,1,15*1024,2);
        return encoder.matches(rawPassword, encodedPassword);
    }
}
