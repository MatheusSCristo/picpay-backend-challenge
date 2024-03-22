package com.matheus.picpay.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ResponseConfig {
    @Bean
    public WebClient webClient(){
        String transactionURL = "https://run.mocky.io/v3";
        return WebClient.builder().baseUrl(transactionURL).build();
    }
}
