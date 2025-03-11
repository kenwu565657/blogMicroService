package com.contentfarm.gateway;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;

@EnableWebFluxSecurity
@EnableDiscoveryClient
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API Gateway", version = "1.0", description = "Documentation API Gateway v1.0"))
public class ContentfarmGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContentfarmGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(r -> r.path("/blogpost-service/v3/api-docs").and().method(HttpMethod.GET).uri("lb://blogpost-service"))
                .route(r -> r.path("/search-service/v3/api-docs").and().method(HttpMethod.GET).uri("lb://search-service"))
                .route(r -> r.path("/multimedia-service/v3/api-docs").and().method(HttpMethod.GET).uri("lb://multimedia-service"))
                .build();
    }

    @Bean
    SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange((authorize) -> authorize
                        .pathMatchers(HttpMethod.GET, "/blogpost/**").permitAll()
                        .pathMatchers(HttpMethod.GET, "/search/**").permitAll()
                        .pathMatchers(HttpMethod.GET, "/multimedia/**").permitAll()
                        .anyExchange().authenticated()
                )
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwkSetUri("http://localhost:9000/oauth2/jwks")
                        )
                )
                ;
        return http.build();
    }

}
