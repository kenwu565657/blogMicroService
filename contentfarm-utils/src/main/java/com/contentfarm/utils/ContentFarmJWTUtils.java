package com.contentfarm.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class ContentFarmJWTUtils {
    public static String generateJWTToken(String subject) {
        return Jwts.builder()
                .signWith(Keys.hmacShaKeyFor("".getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .addClaims(Map.of("userId", ""))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .compact();
    }

    public static String parseJWTToken(String token) {
        Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor("".getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token)
                .getBody();
        return token;
    }
}
