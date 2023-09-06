package ru.kirill.telegramservice.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.kirill.commondto.response.Notification;
import ru.kirill.telegramservice.bot.TelegramBot;

@Component
@RequiredArgsConstructor
@RabbitListener(queues = "${rabbitmq.listen-queue}")
public class NotificationConsumer {

    private final TelegramBot telegramBot;

    @RabbitHandler
    public void handleMessage(Notification notification) {
        telegramBot.sendNotification(notification);
    }
}