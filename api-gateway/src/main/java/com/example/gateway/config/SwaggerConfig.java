package com.example.gateway.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public List<GroupedOpenApi> apis() {
        List<GroupedOpenApi> groups = new ArrayList<>();

        // User Service API Group
        groups.add(GroupedOpenApi.builder()
                .group("user-service")
                .pathsToMatch("/api/users/**", "/api/auth/**")
                .build());

        // Notification Service API Group
        groups.add(GroupedOpenApi.builder()
                .group("notification-service")
                .pathsToMatch("/api/notifications/**")
                .build());

        // Product Service API Group
        groups.add(GroupedOpenApi.builder()
                .group("product-service")
                .pathsToMatch("/api/products/**")
                .build());

        // Order Service API Group
        groups.add(GroupedOpenApi.builder()
                .group("order-service")
                .pathsToMatch("/api/orders/**")
                .build());

        // Payment Service API Group
        groups.add(GroupedOpenApi.builder()
                .group("payment-service")
                .pathsToMatch("/api/payments/**")
                .build());

        return groups;
    }
}
