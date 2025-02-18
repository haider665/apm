package com.elk.apm.service_a.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * @author mojib.haider
 * @since 12/23/24
 */
@AllArgsConstructor
@RestController
@RequestMapping("/serviceA")
public class ServiceAController {

    private final WebClient.Builder webClientBuilder;
    private final RestClient.Builder restClientBuilder;
//    private final HttpServiceProxyFactory proxyFactory;
//    private final ServiceBClient serviceBClient;

    @GetMapping("/get")
    public Mono<ResponseEntity<String>> get() {
        return webClientBuilder.baseUrl("http://service-b")
                .build()
                .get()
                .uri("/serviceB/getMessage")
                .retrieve()
                .bodyToMono(String.class)
                .map(message -> ResponseEntity.ok(message))
                .onErrorResume(error -> {
                    // Handle errors gracefully
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Error occurred: " + error.getMessage()));
                });
    }

    @GetMapping("/get2")
    public Mono<String> get2() {
        return Mono.fromCallable(() ->
                restClientBuilder.baseUrl("http://service-b")
                        .build()
                        .get()
                        .uri("/serviceB/getMessage")
                        .retrieve()
                        .body(String.class)
        ).subscribeOn(Schedulers.boundedElastic());
    }
}

