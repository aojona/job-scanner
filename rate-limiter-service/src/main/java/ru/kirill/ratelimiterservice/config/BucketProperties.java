package ru.kirill.ratelimiterservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.Map;

@ConfigurationProperties(prefix = "bucket")
public record BucketProperties(Map<String, BandwidthProperty> bandwidthMap) {
}
