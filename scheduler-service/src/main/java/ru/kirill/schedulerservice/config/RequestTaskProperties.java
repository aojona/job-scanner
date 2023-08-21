package ru.kirill.schedulerservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import ru.kirill.commondto.request.RequestTask;

@ConfigurationProperties(prefix = "request-task")
public record RequestTaskProperties(RequestTask.QueryParams customQueryParams) {
}
