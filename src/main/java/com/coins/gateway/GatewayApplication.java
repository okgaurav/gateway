package com.coins.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator cryptoRouteConfig(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder.routes()
                .route(p-> p.path("/service/crypto/**")
                        .filters(f-> f.rewritePath("/service/crypto/(?<segment>.*)","/${segment}"))
                        .uri("lb://CRYPTO"))
                .route(p-> p.path("/service/exchange/**")
                        .filters(f-> f.rewritePath("/service/exchange/(?<segment>.*)","/${segment}"))
                        .uri("lb://EXCHANGE"))
                .build();
    }
}