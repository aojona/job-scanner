package ru.kirill.commondto.request;

import lombok.Data;

@Data
public class StatisticsRequest {

    private String query;
    private int numberOfVacancies;
    private int numberOfVacanciesWithSalary;
    private double averageMinSalary;
    private double averageMaxSalary;
}
