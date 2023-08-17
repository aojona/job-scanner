package ru.kirill.vacancyscanservice.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.kirill.commondto.response.RequestTask;
import ru.kirill.commondto.response.VacancyPage;
import ru.kirill.vacancyscanservice.config.SearchProperties;
import ru.kirill.vacancyscanservice.util.QueryUtil;

@Component
@RequiredArgsConstructor
public class SearchClient {

    private final RestTemplate restTemplate;
    private final SearchProperties searchProperties;

    public VacancyPage searchVacancies(RequestTask.QueryParams queryParams) {
        String url = QueryUtil.createUrlWithParameters(searchProperties.url(), queryParams);
        return restTemplate
                .getForEntity(url, VacancyPage.class)
                .getBody();
    }
}
