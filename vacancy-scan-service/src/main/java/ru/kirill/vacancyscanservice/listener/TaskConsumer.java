package ru.kirill.vacancyscanservice.listener;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.kirill.commondto.response.AccessResponse;
import ru.kirill.commondto.request.RequestTask;
import ru.kirill.commondto.response.Vacancy;
import ru.kirill.commondto.response.VacancyResponse;
import ru.kirill.vacancyscanservice.client.SearchClient;
import ru.kirill.vacancyscanservice.feign.RateLimiterClient;
import java.util.List;
import java.util.concurrent.*;

@Component
@RequiredArgsConstructor
@RabbitListener(queues = "${rabbitmq.listen-queue}")
public class TaskConsumer {

    @Value("${rate-limiter.bucket-name}")
    private String bucketName;

    private final SearchClient searchClient;
    private final RabbitTemplate template;
    private final RateLimiterClient rateLimiterClient;
    private final ScheduledExecutorService executorService;

    @RabbitHandler
    public void handleTask(RequestTask task) {
        AccessResponse accessResponse = rateLimiterClient.checkAccess(bucketName);
        executeIfAccess(accessResponse, task);
    }

    @SneakyThrows
    private void executeIfAccess(AccessResponse accessResponse, RequestTask task) {
        if (accessResponse.isAccess()) {
            searchVacancies(task.getQueryParams())
                    .stream()
                    .map(vacancy -> new VacancyResponse(task.getChatId(), vacancy))
                    .forEach(template::convertAndSend);
        } else {
            ScheduledFuture<AccessResponse> scheduledTask = createScheduledTask(accessResponse);
            executeIfAccess(scheduledTask.get(), task);
        }
    }

    private ScheduledFuture<AccessResponse> createScheduledTask(AccessResponse accessResponse) {
        return executorService.schedule(
                () -> rateLimiterClient.checkAccess(bucketName),
                accessResponse.getNanosToWait(),
                TimeUnit.NANOSECONDS
        );
    }

    private List<Vacancy> searchVacancies(RequestTask.QueryParams queryParams) {
        return searchClient
                .searchVacancies(queryParams)
                .getVacancies();
    }
}
