package ru.kirill.ratelimiterservice.service;

import io.github.bucket4j.ConsumptionProbe;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kirill.commondto.response.AccessResponse;
import ru.kirill.ratelimiterservice.config.BucketProperty;
import ru.kirill.ratelimiterservice.exception.BucketNotExistException;

@Service
@RequiredArgsConstructor
public class RateLimiterService {

    private final BucketProperty bucketProperty;

    public AccessResponse checkAccess(String bucketName) {
        ConsumptionProbe consumptionProbe = getConsumptionProbe(bucketName);
        return AccessResponse
                .builder()
                .access(consumptionProbe.isConsumed())
                .nanosToWait(consumptionProbe.getNanosToWaitForRefill())
                .available(consumptionProbe.getRemainingTokens())
                .build();
    }

    private ConsumptionProbe getConsumptionProbe(String bucketName) {
        return bucketProperty
                .getBucket(bucketName)
                .orElseThrow(() -> new BucketNotExistException(bucketName))
                .tryConsumeAndReturnRemaining(bucketProperty.getConsumeNumTokens());
    }
}
