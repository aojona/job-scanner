package ru.kirill.webui.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cookie")
public record CookieProperties(WebCookie removeAccessToken) {
}
