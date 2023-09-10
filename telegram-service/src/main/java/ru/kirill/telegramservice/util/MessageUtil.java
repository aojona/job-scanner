package ru.kirill.telegramservice.util;

import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;

@UtilityClass
public class MessageUtil {

    public SendMessage createReply(long chatId, String text) {
        return SendMessage
                .builder()
                .chatId(chatId)
                .parseMode(ParseMode.HTML)
                .text(text)
                .build();
    }

    public SendPhoto createReply(long chatId, String text, String imageUrl) {
        return SendPhoto
                .builder()
                .chatId(chatId)
                .parseMode(ParseMode.HTML)
                .photo(new InputFile(imageUrl))
                .caption(text)
                .build();
    }
}
