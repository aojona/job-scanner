package ru.kirill.analyticservice.util;

import lombok.Data;
import ru.kirill.commondto.response.Salary;

@Data
public class VacancyAnalyzer {

    private String query;
    private double averageMinSalary;
    private double averageMaxSalary;
    private int numberOfVacancies;
    private int numberOfVacanciesWithSalary;

    public VacancyAnalyzer(String query) {
        this.query = query;
    }

    public void analyze(Salary salary) {
        numberOfVacancies++;
        if (salary != null) {
            numberOfVacanciesWithSalary++;
            averageMinSalary = calculateAvgSalary(averageMinSalary, salary.getFrom());
            averageMaxSalary = calculateAvgSalary(averageMaxSalary, salary.getTo());
        }
    }

    private double calculateAvgSalary(Double averageSalary, Integer salary) {
        if (salary != null) {
            averageSalary = averageSalary + (salary - averageSalary) / numberOfVacancies;
        }
        return averageSalary;
    }
}
