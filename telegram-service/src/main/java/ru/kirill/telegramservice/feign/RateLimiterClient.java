package ru.kirill.telegramservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.kirill.commondto.response.AccessResponse;

@FeignClient(name = "rate-limiter-service")
public interface RateLimiterClient {

    @GetMapping("/limiter/{bucketName}")
    AccessResponse checkAccess(@PathVariable String bucketName);
}
