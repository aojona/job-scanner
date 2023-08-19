package ru.kirill.schedulerservice.scheduler;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import ru.kirill.commondto.request.RequestTask;
import ru.kirill.schedulerservice.config.RequestTaskProperties;
import java.lang.reflect.Field;
import java.util.Arrays;

@Component
public class TaskCustomizer {

    private final RequestTask.QueryParams customQueryParams;

    public TaskCustomizer(RequestTaskProperties taskProperties) {
        this.customQueryParams = taskProperties.customQueryParams();
    }

    public void setCustomQueryParams(RequestTask.QueryParams targetQueryParams) {
        if (customQueryParams != null) {
            Arrays
                    .stream(customQueryParams.getClass().getDeclaredFields())
                    .peek(field -> field.setAccessible(true))
                    .forEach(field -> setFieldValueIfNotNull(targetQueryParams, field));
        }
    }

    @SneakyThrows
    private void setFieldValueIfNotNull(RequestTask.QueryParams queryParams, Field field) {
        Object customValue = field.get(customQueryParams);
        if (customValue != null) {
            field.set(queryParams, customValue);
        }
    }
}
