//package com.spring.cloud.gateway.config;
//
//import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
//import io.github.resilience4j.timelimiter.TimeLimiterConfig;
//import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
//import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
//import org.springframework.cloud.client.circuitbreaker.Customizer;
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.Duration;
//
//@Configuration
//public class GatewayConfig {
//
//    private static final Duration TIMEOUT = Duration.ofSeconds(1);
//
//    @Bean
//    public RouteLocator locatorConfig(RouteLocatorBuilder routeLocatorBuilder){
//
//        return routeLocatorBuilder.routes()
//                .route(x -> x
//                        .path("/products/**")
//                                .filters(filter-> filter.circuitBreaker(config -> config
//                                        .setName("productMicroServiceCircuitBreaker")
//                                        .setFallbackUri("forward:/fallback/productServiceFallback")))
//                        .uri("http://localhost:9092"))
//                .route(x -> x
//                        .path("/books/**")
//                        .uri("http://localhost:9091"))
//                .build();
//
//    }
//
//    @Bean
//    public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
//        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
//                .circuitBreakerConfig(CircuitBreakerConfig.custom()
//                        .slidingWindowSize(20)
//                        .permittedNumberOfCallsInHalfOpenState(5)
//                        .failureRateThreshold(50)
//                        .waitDurationInOpenState(Duration.ofSeconds(60))
//                        .build())
//                .timeLimiterConfig(TimeLimiterConfig.custom()
//                        .timeoutDuration(TIMEOUT)
//                        .build())
//                .build()
//        );
//    }
//}
