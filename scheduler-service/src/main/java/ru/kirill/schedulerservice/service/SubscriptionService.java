package ru.kirill.schedulerservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kirill.commondto.request.AnalyticsTask;
import ru.kirill.commondto.request.RequestTask;
import ru.kirill.schedulerservice.entity.Subscription;
import ru.kirill.schedulerservice.mapper.SubscriptionMapper;
import ru.kirill.schedulerservice.repository.SubscriptionRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;

    public Slice<RequestTask> getSliceOfRequestTasks(Pageable pageable) {
        return subscriptionRepository
                .findAllBy(pageable)
                .map(subscriptionMapper::toDto);
    }

    public Slice<AnalyticsTask> getSliceOfAnalyticsTasks(Pageable pageable) {
        return subscriptionRepository
                .findAllBy(pageable)
                .map(Subscription::getText)
                .map(AnalyticsTask::new);
    }
}
