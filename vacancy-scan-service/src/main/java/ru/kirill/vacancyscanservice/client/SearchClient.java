package ru.kirill.vacancyscanservice.client;

import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;
import ru.kirill.commondto.request.SearchMessage;
import ru.kirill.commondto.response.VacancyPage;
import ru.kirill.vacancyscanservice.annotation.Client;
import ru.kirill.vacancyscanservice.config.SearchProperties;
import ru.kirill.vacancyscanservice.util.QueryUtil;

@Client
@RequiredArgsConstructor
public class SearchClient {

    private final RestTemplate restTemplate;
    private final SearchProperties searchProperties;

    public VacancyPage searchVacancies(SearchMessage query) {
        String url = QueryUtil.createUrlWithParameters(searchProperties.url(), query);
        return restTemplate
                .getForEntity(url, VacancyPage.class)
                .getBody();
    }
}
