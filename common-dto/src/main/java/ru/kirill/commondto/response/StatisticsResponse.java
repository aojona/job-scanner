package ru.kirill.commondto.response;

import lombok.Data;

@Data
public class StatisticsResponse {

    private String query;
    private int numberOfVacancies;
    private int numberOfVacanciesWithSalary;
    private double averageMinSalary;
    private double averageMaxSalary;
}
