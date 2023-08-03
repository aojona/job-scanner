package ru.kirill.ratelimiterservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kirill.commondto.response.AccessResponse;
import ru.kirill.ratelimiterservice.service.RateLimiterService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/limiter")
public class RateLimiterController {

    private final RateLimiterService rateLimiterService;

    @GetMapping("/{bucketName}")
    public AccessResponse checkAccess(@PathVariable String bucketName) {
        return rateLimiterService.checkAccess(bucketName);
    }
}
