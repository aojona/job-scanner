package ru.kirill.ratelimiterservice.config;

import io.github.bucket4j.Bandwidth;
import lombok.Getter;
import java.time.Duration;

@Getter
public class BandwidthProperty {

    private final Bandwidth bandwidth;

    public BandwidthProperty(long capacity, Duration period) {
        this.bandwidth = Bandwidth.simple(capacity, period);
    }
}
