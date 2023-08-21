package ru.kirill.schedulerservice.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.kirill.commondto.request.RequestTask;
import ru.kirill.schedulerservice.service.SubscriptionService;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class TaskProducer {

    private final SubscriptionService subscriptionService;
    private final TaskCustomizer taskCustomizer;
    private final RabbitTemplate template;

    @Value("${scheduler.batch-size}")
    private int batchSize;

    @Scheduled(fixedRateString = "${scheduler.fixed-rate}", timeUnit = TimeUnit.SECONDS)
    public void createTasks() {
        Slice<RequestTask> slice = subscriptionService.getSlice(PageRequest.of(0, batchSize));
        sendTaskWithCustomParams(slice);
        while (slice.hasNext()) {
            slice = subscriptionService.getSlice(slice.nextPageable());
            sendTaskWithCustomParams(slice);
        }
    }

    private void sendTaskWithCustomParams(Slice<RequestTask> slice) {
        slice
                .stream()
                .filter(task -> task.getChatId() != null)
                .peek(task -> taskCustomizer.setCustomQueryParams(task.getQueryParams()))
                .forEach(template::convertAndSend);
    }
}
