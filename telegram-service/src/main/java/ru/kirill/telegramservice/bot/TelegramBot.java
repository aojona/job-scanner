package ru.kirill.telegramservice.bot;

import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.kirill.telegramservice.config.BotProperties;

@Getter
@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final String botUsername;

    public TelegramBot(BotProperties botProperties) {
        super(botProperties.token());
        this.botUsername = botProperties.name();
    }

    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
    }
}
