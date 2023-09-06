package ru.kirill.telegramservice.bot;

import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.kirill.telegramservice.config.BotProperties;

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
                execute(reply);
            }
        }
    }
}