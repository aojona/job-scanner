package ru.kirill.vacancyscanservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kirill.commondto.request.SearchQuery;
import ru.kirill.commondto.response.Vacancy;
import ru.kirill.commondto.response.VacancyPage;
import ru.kirill.vacancyscanservice.client.SearchClient;

@RestController
@RequiredArgsConstructor
@RequestMapping("/scanner")
public class SearchController {

    private final SearchClient searchClient;

    @PostMapping
    public ResponseEntity<Vacancy> scan(@RequestBody SearchQuery query) {
        VacancyPage vacancyPage = searchClient.searchVacancies(query);
        Vacancy vacancy = vacancyPage.getVacancies().get(0);
        return new ResponseEntity<>(vacancy, HttpStatus.OK);
    }
}

