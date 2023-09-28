package ru.kirill.vacancynotifierservice.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import ru.kirill.commondto.response.Notification;
import ru.kirill.commondto.response.VacancyResponse;
import ru.kirill.vacancynotifierservice.service.NotificationService;

@Component
@RequiredArgsConstructor
@RabbitListener(queues = "${rabbitmq.listen-queue}")
public class VacancyConsumer {

    private final NotificationService notificationService;
    private final RabbitTemplate template;

    @RabbitHandler
    public void handleMessage(VacancyResponse vacancyResponse) {
        vacancyResponse
                .getChatIds()
                .forEach(chatId -> {
                    Notification notification = notificationService.createNotification(
                            chatId,
                            vacancyResponse.getVacancy(),
                            vacancyResponse.getQueryText().replaceAll(" ", " #")
                    );
                    template.convertAndSend(notification);
                });
    }
}
