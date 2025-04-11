package com.example.apigatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class FilterConfig {
//    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/first-service/**") // r 이 들어오면 path 를 확인하고 필터 적용하고 uri로 이동시켜주는거다 라고 보면 된다.
                            .filters(f-> f.addRequestHeader("first-request", "first-request-header")
                                            .addResponseHeader("first-response","first-response-header")) // requestHeaer인데 key, value 형태이다.
                            .uri("http://localhost:8081"))
                .route(r -> r.path("/second-service/**") // r 이 들어오면 path 를 확인하고 필터 적용하고 uri로 이동시켜주는거다 라고 보면 된다.
                        .filters(f-> f.addRequestHeader("second-request", "second-request-header")
                                .addResponseHeader("second-response","second-response-header")) // requestHeaer인데 key, value 형태이다.
                        .uri("http://localhost:8082"))
                .build();
    }
}
