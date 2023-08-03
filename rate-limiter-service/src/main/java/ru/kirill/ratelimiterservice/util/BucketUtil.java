package ru.kirill.ratelimiterservice.util;

import io.github.bucket4j.Bucket;
import io.github.bucket4j.local.LocalBucket;
import lombok.experimental.UtilityClass;
import ru.kirill.ratelimiterservice.config.BandwidthProperty;
import java.util.Map;
import java.util.stream.Collectors;

@UtilityClass
public class BucketUtil {

    public static Map<String,Bucket> createBucketMap(Map<String, BandwidthProperty> bandwidthMap) {
        return bandwidthMap
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        BucketUtil::createBucket
                ));
    }

    public static LocalBucket createBucket(Map.Entry<String,BandwidthProperty> entry) {
        return Bucket
                .builder()
                .addLimit(entry.getValue().getBandwidth())
                .build();
    }
}
