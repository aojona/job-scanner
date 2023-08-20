package ru.kirill.vacancystorageservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.temporal.ChronoUnit;

@ConfigurationProperties(prefix = "vacancy")
public record VacancyProperties(long noveltyTime, ChronoUnit unit) {
}
