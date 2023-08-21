package ru.kirill.telegramservice.config;

import org.springframework.cloud.netflix.eureka.http.EurekaClientHttpRequestFactorySupplier;
import org.springframework.cloud.netflix.eureka.http.RestTemplateDiscoveryClientOptionalArgs;
import org.springframework.cloud.netflix.eureka.http.RestTemplateTransportClientFactories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiscoveryClientConfiguration {

    @Bean
    public RestTemplateDiscoveryClientOptionalArgs restTemplateDiscoveryClientOptionalArgs(
            EurekaClientHttpRequestFactorySupplier factorySupplier) {
        return new RestTemplateDiscoveryClientOptionalArgs(factorySupplier);
    }

    @Bean
    public RestTemplateTransportClientFactories restTemplateTransportClientFactories(
            RestTemplateDiscoveryClientOptionalArgs optionalArgs) {
        return new RestTemplateTransportClientFactories(optionalArgs);
    }
}