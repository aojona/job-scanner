package ru.kirill.vacancyscanservice.listener;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import ru.kirill.commondto.request.SearchMessage;
import ru.kirill.commondto.response.AccessResponse;
import ru.kirill.vacancyscanservice.annotation.Consumer;
import ru.kirill.vacancyscanservice.client.SearchClient;
import ru.kirill.vacancyscanservice.feign.RateLimiterClient;
import java.util.concurrent.*;

@Consumer
@RequiredArgsConstructor
@RabbitListener(queues = "${rabbitmq.scheduled-task-queue}")
public class TaskConsumer {

    private final SearchClient searchClient;
    private final RabbitTemplate template;
    private final RateLimiterClient rateLimiterClient;
    private final ScheduledExecutorService executorService;

    @RabbitHandler
    public void handleSearchMessageTask(SearchMessage searchMessage) {
        AccessResponse accessResponse = rateLimiterClient.checkAccess("vacancy-scan-service");
        executeIfAccess(accessResponse, searchMessage);
    }

    @SneakyThrows
    private void executeIfAccess(AccessResponse accessResponse, SearchMessage searchMessage) {
        if (accessResponse.isAccess()) {
            searchVacancies(searchMessage);
        } else {
            ScheduledFuture<AccessResponse> scheduledTask = createScheduledTask(accessResponse);
            executeIfAccess(scheduledTask.get(), searchMessage);
        }
    }

    @NotNull
    private ScheduledFuture<AccessResponse> createScheduledTask(AccessResponse accessResponse) {
        return executorService.schedule(
                () -> rateLimiterClient.checkAccess("vacancy-scan-service"),
                accessResponse.getNanosToWait(),
                TimeUnit.NANOSECONDS
        );
    }

    private void searchVacancies(SearchMessage searchMessage) {
        searchClient
                .searchVacancies(searchMessage)
                .getVacancies()
                .forEach(template::convertAndSend);
    }
}
