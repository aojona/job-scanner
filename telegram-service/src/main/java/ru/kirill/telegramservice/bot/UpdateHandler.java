package ru.kirill.telegramservice.bot;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.kirill.telegramservice.util.MessageUtil;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class UpdateHandler {

    private static final String START = "/start";
    private static final Locale LOCALE = Locale.ENGLISH;

    private final MessageSource messageSource;

    public SendMessage handleMessage(Message message) {
        String data = message.getText();
        return switch(data) {
            case START  -> handleStartMessage(message.getChat());
            default     -> null;
        };
    }

    private SendMessage handleStartMessage(Chat chat) {
        Object[] messageArgs = {chat.getFirstName(), chat.getId()};
        String message = messageSource.getMessage(START, messageArgs, LOCALE);
        return MessageUtil.createReply(chat.getId(), message);
    }
}
