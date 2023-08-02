package ru.kirill.vacancystorageservice.config;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.concurrent.TimeUnit;

@Data
@ConfigurationProperties(prefix = "redis")
public class RedisProperties {

    private TimeUnit timeUnit;
    private long time;
    private long timeToLive;

    @PostConstruct
    private void initTimeToLive() {
        timeToLive = timeUnit.toSeconds(time);
    }
}
