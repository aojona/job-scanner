package ru.kirill.vacancyscanservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kirill.vacancyscanservice.client.SearchClient;
import ru.kirill.vacancyscanservice.dto.request.SearchQuery;
import ru.kirill.vacancyscanservice.dto.response.Vacancy;
import ru.kirill.vacancyscanservice.dto.response.VacancyPage;

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

