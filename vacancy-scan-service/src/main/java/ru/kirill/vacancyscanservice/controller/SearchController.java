package ru.kirill.vacancyscanservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kirill.vacancyscanservice.client.SearchClient;
import ru.kirill.vacancyscanservice.dto.request.SearchQuery;
import ru.kirill.vacancyscanservice.dto.response.VacancyPage;

@RestController
@RequiredArgsConstructor
public class SearchController {

    private final SearchClient searchClient;

    @PostMapping
    public ResponseEntity<VacancyPage> scan(@RequestBody SearchQuery query) {
        return ResponseEntity.ok(searchClient.searchVacancies(query));
    }
}
