package com.example.demo;


import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@org.springframework.context.annotation.Configuration
public class Cofiguration {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
