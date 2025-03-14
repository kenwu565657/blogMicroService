package com.contentfarm.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
public class RouteConfiguration {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(r -> r.path("/blogpost-service/v3/api-docs").and().method(HttpMethod.GET).uri("lb://blogpost-service"))
                .route(r -> r.path("/search-service/v3/api-docs").and().method(HttpMethod.GET).uri("lb://search-service"))
                .route(r -> r.path("/multimedia-service/v3/api-docs").and().method(HttpMethod.GET).uri("lb://multimedia-service"))
                .build();
    }
}
