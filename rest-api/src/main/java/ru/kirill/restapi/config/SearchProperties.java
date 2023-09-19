package ru.kirill.restapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "search")
public record SearchProperties(int page, int size, int range) {
}
