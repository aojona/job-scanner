package ru.kirill.vacancyscanservice.client;

import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;
import ru.kirill.vacancyscanservice.annotation.Client;
import ru.kirill.vacancyscanservice.config.SearchProperties;
import ru.kirill.vacancyscanservice.model.SearchQuery;
import ru.kirill.vacancyscanservice.util.QueryUtil;

@Client
@RequiredArgsConstructor
public class SearchClient {

    private final RestTemplate restTemplate;
    private final SearchProperties searchProperties;

    public void searchVacancies(SearchQuery query) {
        String url = QueryUtil.createUrlWithParameters(searchProperties.url(), query);
        String result = restTemplate.getForObject(url, String.class);
        System.out.println(result);
    }
}
