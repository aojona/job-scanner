package ru.kirill.schedulerservice.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.kirill.commondto.request.SearchMessage;

import java.util.concurrent.TimeUnit;

@Component
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
                .text("Hello World")
                .page(10)
                .perPage(15)
                .build();
    }
}
