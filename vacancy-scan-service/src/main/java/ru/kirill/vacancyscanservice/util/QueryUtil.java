package ru.kirill.vacancyscanservice.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.springframework.web.util.UriComponentsBuilder;
import java.lang.reflect.Field;
import java.util.Arrays;

@UtilityClass
public class QueryUtil {

    public static <Q> String createUrlWithParameters(String url, String path, Q query) {
        return Arrays
                .stream(query.getClass().getDeclaredFields())
                .map(Field::getName)
                .map(name -> getField(query, name))
                .peek(field -> field.setAccessible(true))
                .filter(field -> getFieldValue(query, field) != null)
                .reduce(
                        UriComponentsBuilder.fromHttpUrl(url).path(path),
                        (uriBuilder, field) -> uriBuilder.queryParam(
                                convertToSnakeCase(field.getName()),
                                getFieldValue(query, field)
                        ),
                        (result, notFinal) -> result
                )
                .build()
                .toUriString();
    }

    @SneakyThrows
    private static <Q> Field getField(Q query, String fieldName) {
        return query.getClass().getDeclaredField(fieldName);
    }

    @SneakyThrows
    private static <Q> Object getFieldValue(Q query, Field field) {
        return field.get(query);
    }

    private static String convertToSnakeCase(String str) {
        return str
                .replaceAll("([a-w])([A-W]+)","$1_$2")
                .toLowerCase();
    }
}
