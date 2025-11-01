package com.example.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SwaggerController {

    private final WebClient.Builder webClientBuilder;

    // Map of service names to their base URLs
    private static final Map<String, String> SERVICE_URLS = new HashMap<>() {{
        put("user-service", "http://user-service:8081");
        put("notification-service", "http://notification-service:8082");
        put("product-service", "http://product-service:8083");
        put("order-service", "http://order-service:8084");
        put("payment-service", "http://payment-service:8085");
    }};

    @Autowired
    public SwaggerController(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    /**
     * Fetches OpenAPI documentation from individual microservices
     * This allows the Gateway to aggregate all API docs in one place
     */
    @GetMapping("/v3/api-docs/{service}")
    public Mono<ResponseEntity<String>> getServiceApiDocs(@PathVariable String service) {
        String serviceUrl = SERVICE_URLS.get(service);

        if (serviceUrl == null) {
            return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Service not found: " + service));
        }

        return webClientBuilder.build()
                .get()
                .uri(serviceUrl + "/v3/api-docs")
                .retrieve()
                .bodyToMono(String.class)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                        .body("Service unavailable: " + service)));
    }

    /**
     * Health check endpoint
     */
    @GetMapping("/health")
    public Mono<ResponseEntity<Map<String, String>>> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "API Gateway");
        return Mono.just(ResponseEntity.ok(response));
    }
}
