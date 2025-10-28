package com.rentflex.apigateway.config;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Configuration
public class SwaggerConfig {

    private final WebClient webClient = WebClient.create();

    private Mono<Map<String, Object>> fetchMergedDocs() {
        Mono<Map<String, Object>> booking = fetchDocs("http://localhost:8082/v3/api-docs");
        Mono<Map<String, Object>> inventory = fetchDocs("http://localhost:8083/v3/api-docs");
        Mono<Map<String, Object>> notification = fetchDocs("http://localhost:8084/v3/api-docs");
        Mono<Map<String, Object>> payment = fetchDocs("http://localhost:8085/v3/api-docs");
        Mono<Map<String, Object>> user = fetchDocs("http://localhost:8086/v3/api-docs");
        Mono<Map<String, Object>> vendor = fetchDocs("http://localhost:8087/v3/api-docs");

        return Mono.zip(user, vendor, inventory, booking, notification, payment)
                .map(
                        tuple -> {
                            Map<String, Object> merged = new LinkedHashMap<>();
                            merged.put("openapi", "3.0.1");
                            merged.put(
                                    "info",
                                    Map.of("title", "RentFlex API Gateway", "version", "1.0.0"));

                            // Merge paths
                            Map<String, Object> mergedPaths = new LinkedHashMap<>();
                            Stream.of(
                                            tuple.getT1(),
                                            tuple.getT2(),
                                            tuple.getT3(),
                                            tuple.getT4(),
                                            tuple.getT5(),
                                            tuple.getT6())
                                    .filter(Objects::nonNull)
                                    .forEach(
                                            map ->
                                                    mergedPaths.putAll(
                                                            (Map<String, Object>)
                                                                    map.getOrDefault(
                                                                            "paths", Map.of())));
                            merged.put("paths", mergedPaths);

                            // Deep merge components
                            Map<String, Object> mergedComponents = new LinkedHashMap<>();
                            Stream.of(
                                            tuple.getT1(),
                                            tuple.getT2(),
                                            tuple.getT3(),
                                            tuple.getT4(),
                                            tuple.getT5(),
                                            tuple.getT6())
                                    .filter(Objects::nonNull)
                                    .forEach(
                                            map -> {
                                                Map<String, Object> components =
                                                        (Map<String, Object>)
                                                                map.getOrDefault(
                                                                        "components", Map.of());
                                                components.forEach(
                                                        (key, value) -> {
                                                            Map<String, Object> existing =
                                                                    (Map<String, Object>)
                                                                            mergedComponents
                                                                                    .getOrDefault(
                                                                                            key,
                                                                                            new LinkedHashMap<>());
                                                            existing.putAll(
                                                                    (Map<String, Object>) value);
                                                            mergedComponents.put(key, existing);
                                                        });
                                            });
                            merged.put("components", mergedComponents);

                            return merged;
                        });
    }

    private Mono<Map<String, Object>> fetchDocs(String uri) {
        return webClient
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                .onErrorResume(
                        e -> {
                            System.err.println(
                                    "Failed to fetch docs from " + uri + ": " + e.getMessage());
                            return Mono.just(Map.of());
                        });
    }

    @Bean
    public RouterFunction<ServerResponse> swaggerRouter() {
        return RouterFunctions.route(
                RequestPredicates.GET("/merged-api-docs"),
                request ->
                        ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(fetchMergedDocs(), Map.class));
    }
}
