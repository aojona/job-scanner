package ru.kirill.telegramservice.listener;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.kirill.commondto.response.AccessResponse;
import ru.kirill.commondto.response.Notification;
import ru.kirill.telegramservice.bot.TelegramBot;
import ru.kirill.telegramservice.feign.RateLimiterClient;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
@RabbitListener(queues = "${rabbitmq.listen-queue}")
public class NotificationConsumer {

    @Value("${rate-limiter.bucket-name}")
    private String bucketName;

    private final TelegramBot telegramBot;
    private final RateLimiterClient rateLimiterClient;
    private final ScheduledExecutorService executorService;

    @RabbitHandler
    public void handleMessage(Notification notification) {
        AccessResponse accessResponse = rateLimiterClient.checkAccess(bucketName);
        executeIfAccess(accessResponse, notification);
    }


    @SneakyThrows
    private void executeIfAccess(AccessResponse accessResponse, Notification notification) {
        if (accessResponse.isAccess()) {
            try {
                telegramBot.sendNotification(notification);
            } catch (TelegramApiException e) {
                log.error("Sending message exception for chat id = " + notification.getChatId());
            }
        } else {
            ScheduledFuture<AccessResponse> scheduledTask = createScheduledTask(accessResponse);
            executeIfAccess(scheduledTask.get(), notification);
        }
    }

    private ScheduledFuture<AccessResponse> createScheduledTask(AccessResponse accessResponse) {
        return executorService.schedule(
                () -> rateLimiterClient.checkAccess(bucketName),
                accessResponse.getNanosToWait(),
                TimeUnit.NANOSECONDS
        );
    }
}