package com.contentfarm.auth.server.service;

import jakarta.annotation.Nullable;
import org.springframework.security.crypto.keygen.Base64StringKeyGenerator;
import org.springframework.security.crypto.keygen.StringKeyGenerator;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationCode;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;

import java.time.Instant;
import java.util.Base64;

/**
 * 自定义OAuth2授权码生成器
 * <p>Customized by {@link org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationProvider}.OAuth2AuthorizationCodeGenerator
 *
 * @author MaoAtao
 * 2022-10-06 21:47:14
 */

public class CustomAuthorizationCodeGenerator implements OAuth2TokenGenerator<OAuth2AuthorizationCode> {

    private final StringKeyGenerator authorizationCodeGenerator = new Base64StringKeyGenerator(Base64.getUrlEncoder().withoutPadding(), 96);

    public CustomAuthorizationCodeGenerator() {
    }

    @Override
    @Nullable
    public OAuth2AuthorizationCode generate(OAuth2TokenContext context) {
        if (context.getTokenType() != null && "code".equals(context.getTokenType().getValue())) {
            Instant issuedAt = Instant.now();
            Instant expiresAt = issuedAt.plus(context.getRegisteredClient().getTokenSettings().getAuthorizationCodeTimeToLive());
            return new OAuth2AuthorizationCode(this.authorizationCodeGenerator.generateKey(), issuedAt, expiresAt);
        } else {
            return null;
        }
    }
}
