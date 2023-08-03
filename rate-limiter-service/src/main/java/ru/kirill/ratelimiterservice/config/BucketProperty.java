package ru.kirill.ratelimiterservice.config;

import io.github.bucket4j.Bucket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
public class BucketProperty {

    @Getter
    private final long consumeNumTokens;
    private final Map<String, Bucket> bucketMap;

    public Optional<Bucket> getBucket(String key) {
        return Optional.ofNullable(bucketMap.get(key));
    }
}
