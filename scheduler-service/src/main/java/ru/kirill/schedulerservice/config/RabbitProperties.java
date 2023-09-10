package ru.kirill.schedulerservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rabbitmq")
public record RabbitProperties (String exchange,
                                String vacancyScanKey,
                                String analyticsKey) {
}
