package ru.kirill.commondto.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class StatisticsRequest {

    private String query;
    private int numberOfVacancies;
    private int numberOfVacanciesWithSalary;
    private double averageMinSalary;
    private double averageMaxSalary;
    private LocalDate date;
}
