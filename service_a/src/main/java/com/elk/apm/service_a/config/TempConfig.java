package com.elk.apm.service_a.config;

import io.micrometer.observation.ObservationRegistry;
import org.springframework.boot.autoconfigure.web.client.RestClientBuilderConfigurer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

/**
 * @author mojib.haider
 * @since 12/24/24
 */
@Configuration
public class TempConfig {

    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    @LoadBalanced
    public RestClient.Builder restClientBuilder() {
        return RestClient.builder();
    }

//    @Bean
//    public HttpServiceProxyFactory httpServiceProxyFactory(RestClient.Builder builder) {
//        RestClient restClient = builder
//                .baseUrl("http://service-b")
//                .build();
//
//        return HttpServiceProxyFactory
//                .builderFor(RestClientAdapter.create(restClient))
//                .build();
//    }
}
