package ru.kirill.restapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import ru.kirill.restapi.security.JwtKey;

@ConfigurationProperties(prefix = "jwt")
public record JwtProperties (JwtKey accessKey) {
}