package com.si.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    private final String backendUrl;

    public WebClientConfig(@Value("${backend.url}") String backendUrl) {
        this.backendUrl = backendUrl;
    }

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(backendUrl)
                .build();
    }
}
