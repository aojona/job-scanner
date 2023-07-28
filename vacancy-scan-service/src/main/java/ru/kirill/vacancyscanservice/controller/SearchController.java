package ru.kirill.vacancyscanservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kirill.vacancyscanservice.client.SearchClient;
import ru.kirill.vacancyscanservice.model.SearchQuery;

@RestController
@RequiredArgsConstructor
public class SearchController {

    private final SearchClient searchClient;

    @PostMapping
    public void scan(@RequestBody SearchQuery query) {
        System.out.println(query.getText());
        searchClient.searchVacancies(query);
    }
}
