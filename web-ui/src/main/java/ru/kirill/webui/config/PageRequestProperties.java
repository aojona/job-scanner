package ru.kirill.webui.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import ru.kirill.commondto.request.PageableRequest;

@ConfigurationProperties(prefix = "page")
public record PageRequestProperties(PageableRequest request) {
}
