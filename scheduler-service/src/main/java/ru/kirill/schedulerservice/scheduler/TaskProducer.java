package ru.kirill.schedulerservice.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.kirill.commondto.request.AnalyticsTask;
import ru.kirill.commondto.request.RequestTask;
import ru.kirill.schedulerservice.config.RabbitProperties;
import ru.kirill.schedulerservice.service.SubscriptionService;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class TaskProducer {

    private final SubscriptionService subscriptionService;
    private final TaskCustomizer taskCustomizer;
    private final RabbitTemplate template;
    private final RabbitProperties rabbitProperties;

    @Value("${scheduler.batch-size}")
    private int batchSize;

    @Scheduled(fixedRateString = "${scheduler.fixed-rate}", timeUnit = TimeUnit.SECONDS)
    public void createVacancyScanTasks() {
        Slice<RequestTask> slice = subscriptionService.getSliceOfRequestTasks(PageRequest.of(0, batchSize));
        sendRequestTaskWithCustomParams(slice);
        while (slice.hasNext()) {
            slice = subscriptionService.getSliceOfRequestTasks(slice.nextPageable());
            sendRequestTaskWithCustomParams(slice);
        }
    }

    private void sendRequestTaskWithCustomParams(Slice<RequestTask> slice) {
        slice
                .stream()
                .peek(task -> taskCustomizer.setCustomQueryParams(task.getQueryParams()))
                .forEach(task -> template.convertAndSend(rabbitProperties.vacancyScanKey(), task));
    }

    @Scheduled(fixedRateString = "${scheduler.fixed-rate}", timeUnit = TimeUnit.SECONDS)
    private void createVacancyAnalyticTasks() {
        Slice<AnalyticsTask> slice = subscriptionService.getSliceOfAnalyticsTasks(PageRequest.of(0, batchSize));
        sendAnalyticsTask(slice);
        while (slice.hasNext()) {
            slice = subscriptionService.getSliceOfAnalyticsTasks(slice.nextPageable());
            sendAnalyticsTask(slice);
        }
    }

    private void sendAnalyticsTask(Slice<AnalyticsTask> slice) {
        slice.forEach(task -> template.convertAndSend(rabbitProperties.analyticsKey(), task));
    }
}
