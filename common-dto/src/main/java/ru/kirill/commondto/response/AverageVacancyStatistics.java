package ru.kirill.commondto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AverageVacancyStatistics {

    private String query;
    private double averageMinSalary;
    private double averageMaxSalary;
    private long numberOfVacancies;
    private long numberOfVacanciesWithSalary;
}
