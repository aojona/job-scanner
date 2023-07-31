package ru.kirill.schedulerservice.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import ru.kirill.commondto.request.SearchMessage;
import ru.kirill.schedulerservice.annotation.Scheduler;
import java.util.concurrent.TimeUnit;

@Scheduler
@RequiredArgsConstructor
public class TaskProducer {

    private final RabbitTemplate template;

    @Scheduled(fixedRateString = "${scheduler.fixed-rate}", timeUnit = TimeUnit.SECONDS)
    public void sendTaskToVacancyScanService() {
        SearchMessage message = buildHelloWorldMessage();
        template.convertAndSend(message);
    }

    private static SearchMessage buildHelloWorldMessage() {
        return SearchMessage
                .builder()
                .text("java")
                .page(10)
                .perPage(15)
                .build();
    }
}
