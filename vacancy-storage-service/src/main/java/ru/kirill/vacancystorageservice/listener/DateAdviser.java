package ru.kirill.vacancystorageservice.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.kirill.vacancystorageservice.config.VacancyProperties;
import java.time.ZonedDateTime;

@Component
@RequiredArgsConstructor
public class DateAdviser {

    private final VacancyProperties vacancyProperties;

    public boolean isNew(ZonedDateTime zonedDateTime) {
        return ZonedDateTime
                .now()
                .minus(vacancyProperties.noveltyTime(), vacancyProperties.unit())
                .isBefore(zonedDateTime);
    }
}
