package ru.kirill.vacancyscanservice.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties(SearchProperties.class)
public class SearchConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
