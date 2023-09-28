package ru.kirill.analyticservice.util;

import lombok.Data;
import ru.kirill.commondto.response.Salary;

import java.time.LocalDate;

@Data
public class VacancyAnalyzer {

    private final String query;
    private final LocalDate date;
    private double averageMinSalary;
    private double averageMaxSalary;
    private int numberOfVacancies;
    private int numberOfVacanciesWithSalary;
    private String AVAILABLE_CURRENCY = "RUR";

    public VacancyAnalyzer(String query, LocalDate date) {
        this.query = query;
        this.date = date;
    }

    public void analyze(Salary salary) {
        numberOfVacancies++;
        if (salary != null) {
            numberOfVacanciesWithSalary++;
            if (salary.getCurrency().equals(AVAILABLE_CURRENCY)) {
                averageMinSalary = calculateAvgSalary(averageMinSalary, salary.getFrom());
                averageMaxSalary = calculateAvgSalary(averageMaxSalary, salary.getTo());
            }
        }
    }

    private double calculateAvgSalary(Double averageSalary, Integer salary) {
        if (salary != null) {
            averageSalary = averageSalary + (salary - averageSalary) / numberOfVacancies;
        }
        return averageSalary;
    }
}
