package ru.kirill.commondto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class VacancyPage {

    @JsonProperty("items")
    List<Vacancy> vacancies;
    int found;
    int pages;
    int perPage;
    int page;
}

