package ru.kirill.vacancynotifierservice.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.kirill.commondto.response.LogoUrl;
import ru.kirill.commondto.response.Notification;
import ru.kirill.commondto.response.VacancyResponse;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final MessageSource messageSource;
    private final Locale locale = Locale.getDefault();

    public Notification createNotification(VacancyResponse vacancyResponse) {
        LogoUrl logoUrls = vacancyResponse.getVacancy().getEmployer().getLogoUrls();
        return new Notification(
                vacancyResponse.getChatId(),
                createNotificationText(vacancyResponse.getVacancy()),
                logoUrls != null ? logoUrls.getOriginal() : null
                );
    }

    public <V> String createNotificationText(V v) {
        Class<?> clazz = v.getClass();
        String code = clazz.getSimpleName().toLowerCase();
        StringBuilder text = new StringBuilder();
        appendMessages(text, v, code, clazz);
        return text.toString();
    }

    private <V> void appendMessages(StringBuilder text, V v, String code, Class<?> clazz) {
        Arrays
                .stream(clazz.getDeclaredFields())
                .peek(field -> field.setAccessible(true))
                .filter(field -> getFieldValue(v, field) != null)
                .forEach(field -> {
                    if (field.getType().getClassLoader() == ClassLoader.getSystemClassLoader()) {
                        appendMessages(text, getFieldValue(v, field), code + "." + field.getName(), field.getType());
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
        Object[] args = {getFieldValue(v, field)};
        return messageSource.getMessage(code + "." + field.getName(), args, "", locale);
    }

    @SneakyThrows
    private static <V> Object getFieldValue(V v, Field field) {
        return field.get(v);
    }
}
