package ru.kirill.telegramservice.bot;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.kirill.commondto.response.Notification;
import ru.kirill.telegramservice.config.BotProperties;
import ru.kirill.telegramservice.util.MessageUtil;

@Slf4j
@Getter
@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final String botUsername;
    private final UpdateHandler updateHandler;

    public TelegramBot(BotProperties botProperties, UpdateHandler updateHandler) {
        super(botProperties.token());
        this.botUsername = botProperties.name();
        this.updateHandler = updateHandler;
    }

    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            SendMessage reply = updateHandler.handleMessage(update.getMessage());
            if (reply != null) {
                try {
                    execute(reply);
                } catch (TelegramApiException e) {
                    log.error("Error while sending message with image to chat with id = " + reply.getChatId());
                }
            }
        }
    }

    public void sendNotification(Notification notification) {
        if (notification.getLogoUrl() != null && !notification.getLogoUrl().contains("bmp")) {
            SendPhoto reply = MessageUtil.createReply(notification.getChatId(), notification.getText(), notification.getLogoUrl());
            try {
                execute(reply);
            } catch (TelegramApiException e) {
                log.error("Error while sending message with image to chat with id = " + notification.getChatId());
            }
        } else {
            SendMessage reply = MessageUtil.createReply(notification.getChatId(), notification.getText());
            try {
                execute(reply);
            } catch (TelegramApiException e) {
                log.error("Error while sending text message to chat with id = " + notification.getChatId());
            }
        }
    }
}