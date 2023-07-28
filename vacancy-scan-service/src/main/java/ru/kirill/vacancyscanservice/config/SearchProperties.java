package ru.kirill.vacancyscanservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "search")
public record SearchProperties(String url) {
}
