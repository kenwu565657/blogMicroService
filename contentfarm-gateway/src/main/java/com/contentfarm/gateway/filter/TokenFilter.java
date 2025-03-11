package com.contentfarm.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.http.HttpResponse;
import java.util.Optional;

// just example, not used
//@Component
public class TokenFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest httpRequest = exchange.getRequest();
        ServerHttpResponse httpResponse = exchange.getResponse();
        if (httpRequest.getMethod() == HttpMethod.GET) {
            return chain.filter(exchange);
        }
        String requestUrl = httpRequest.getURI().getPath();
        Optional<String> token = getToken(httpRequest);
        if (token.isPresent()) {
            return chain.filter(exchange);
        }
        if (true) {
            return chain.filter(exchange);
        } else {
            httpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
            return httpResponse.setComplete();
        }
    }

    private Optional<String> getToken(HttpRequest httpRequest) {
        return Optional.ofNullable(httpRequest.getHeaders().getFirst("Authorization"));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
