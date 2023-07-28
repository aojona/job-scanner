package ru.kirill.vacancyscanservice.client;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.kirill.vacancyscanservice.annotation.Client;
import ru.kirill.vacancyscanservice.config.SearchProperties;
import ru.kirill.vacancyscanservice.model.SearchQuery;

import java.lang.reflect.Field;
import java.util.Arrays;

@Client
@RequiredArgsConstructor
public class SearchClient {

    private final RestTemplate restTemplate;
    private final SearchProperties searchProperties;

    public void searchVacancies(SearchQuery query) {

        String url = Arrays
                .stream(query.getClass().getDeclaredFields())
                .map(Field::getName)
                .reduce(
                        UriComponentsBuilder.fromHttpUrl(searchProperties.url()),
                        (uriBuilder, name) -> uriBuilder.queryParam(name, getFieldValue(query, name)),
                        (result, notFinal) -> result
                )
                .toUriString();

        String result = restTemplate.getForObject(url, String.class);
        System.out.println(result);
    }

    @SneakyThrows
    public String getFieldValue(SearchQuery query, String fieldName) {
        Field field = query.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(query).toString();
    }
}
