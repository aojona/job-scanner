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
                .reduce(
                        UriComponentsBuilder.fromHttpUrl(url).path(path),
                        (uriBuilder, name) -> uriBuilder.queryParam(
                                convertToSnakeCase(name),
                                getFieldValue(query, name)
                        ),
                        (result, notFinal) -> result
                )
                .build()
                .toUriString();
    }

    @SneakyThrows
    private static <Q> Object getFieldValue(Q query, String fieldName) {
        Field field = query.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(query).toString();
    }

    private static String convertToSnakeCase(String str) {
        return str
                .replaceAll("([a-w])([A-W]+)","$1_$2")
                .toLowerCase();
    }
}
