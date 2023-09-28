package ru.kirill.vacancynotifierservice.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.kirill.commondto.response.LogoUrl;
import ru.kirill.commondto.response.Notification;
import ru.kirill.commondto.response.Vacancy;
import ru.kirill.vacancynotifierservice.enums.Currency;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final MessageSource messageSource;
    private final Locale locale = Locale.getDefault();

    public Notification createNotification(Long chatId, Vacancy vacancy, String tag) {
        LogoUrl logoUrls = vacancy.getEmployer().getLogoUrls();
        return new Notification(
                chatId,
                createNotificationText(vacancy, tag),
                logoUrls != null ? logoUrls.getOriginal() : null
        );
    }

    public <V> String createNotificationText(V v, String tag) {
        Class<?> clazz = v.getClass();
        String code = clazz.getSimpleName().toLowerCase();
        String tagMessage = messageSource.getMessage("tag", new Object[]{tag}, locale);
        StringBuilder text = new StringBuilder(tagMessage).append("\n\n");
        appendMessages(text, v, code, clazz);
        return text.toString();
    }

    private <V> void appendMessages(StringBuilder text, V v, String code, Class<?> clazz) {
        Arrays
                .stream(clazz.getDeclaredFields())
                .peek(field -> field.setAccessible(true))
                .filter(field -> getFieldValue(v, field) != null)
                .forEach(field -> {
                    if (field.getType().getClassLoader() != null) {
                        appendMessages(text, getFieldValue(v, field) , code + "." + field.getName(), field.getType());
                    } else {
                        appendMessageIfNotBlank(text, getMessageForFieldValue(v, field, code));
                    }
                });
    }

    private static void appendMessageIfNotBlank(StringBuilder text, String message) {
        if (message != null && !message.isBlank()) {
            text.append(message).append("\n");
        }
    }

    private <V> String getMessageForFieldValue(V v, Field field, String code) {
        Object[] args = {getFieldValue(v, field).toString().replaceAll("<[\\w/]*>","")};
        return messageSource.getMessage(code + "." + field.getName(), args, "", locale);
    }

    @SneakyThrows
    private static <V> Object getFieldValue(V v, Field field) {
        Object fieldValue = field.get(v);
        String fieldName = field.getName();
        if (fieldName.equals(Currency.fieldName)) {
            Optional<Currency> currency = Currency.getCurrency(fieldValue.toString());
            if (currency.isPresent()) {
                fieldValue = currency.get().getDescription();
            }
        }
        return fieldValue;
    }
}
