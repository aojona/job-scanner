package ru.kirill.ratelimiterservice.config;

import io.github.bucket4j.Bucket;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kirill.ratelimiterservice.util.BucketUtil;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(BucketProperties.class)
public class RateLimiterConfig {

    private final BucketProperties bucketProperties;

    @Bean
    public BucketProperty bucketProperty() {
        Map<String, Bucket> bucketMap = BucketUtil.createBucketMap(bucketProperties.bandwidthMap());
        return new BucketProperty(1L, bucketMap);
    }
}
