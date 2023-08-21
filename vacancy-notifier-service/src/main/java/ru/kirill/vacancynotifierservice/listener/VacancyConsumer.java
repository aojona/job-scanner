package ru.kirill.vacancynotifierservice.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.kirill.commondto.response.VacancyResponse;
import ru.kirill.vacancynotifierservice.service.NotificationService;

@Component
@RequiredArgsConstructor
@RabbitListener(queues = "${rabbitmq.listen-queue}")
public class VacancyConsumer {

    private final NotificationService notificationService;

    @RabbitHandler
    public void handleMessage(VacancyResponse vacancyResponse) {
        String notification = notificationService.createNotificationText(vacancyResponse.getVacancy());
    }
}
