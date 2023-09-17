package ru.kirill.telegramservice.bot;

import lombok.Getter;
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
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            SendMessage reply = updateHandler.handleMessage(update.getMessage());
            if (reply != null) {
                try {
                    execute(reply);
                } catch (TelegramApiException e) {
                    log.error("Sending message exception for chat id = " + reply.getChatId());
                }
            }
        }
    }

    public void sendNotification(Notification notification) throws TelegramApiException {
        if (notification.getLogoUrl() != null && !notification.getLogoUrl().contains("bmp")) {
            SendPhoto reply = MessageUtil.createReply(notification.getChatId(), notification.getText(), notification.getLogoUrl());
            execute(reply);
        } else {
            SendMessage reply = MessageUtil.createReply(notification.getChatId(), notification.getText());
            execute(reply);
        }
    }
}