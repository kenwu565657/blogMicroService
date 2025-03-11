package com.contentfarm.gateway.customize;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;

import java.util.function.Predicate;

// just example, not used
public class TestRoutePredicateFactory extends AbstractRoutePredicateFactory<TestRoutePredicateFactory.Config> {

    public TestRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return new GatewayPredicate() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                ServerHttpRequest request = serverWebExchange.getRequest();
                request.getQueryParams().getFirst("");
                return false;
            }
        };
    }

    public static class Config {
        private String param;
        private String value;
    }
}
