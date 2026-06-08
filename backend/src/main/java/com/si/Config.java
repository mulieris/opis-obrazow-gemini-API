package com.si;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;

@Configuration
class Config {

    @Bean
    public HttpClient getHttpClient() {
        return HttpClient.newHttpClient();
    }
}
