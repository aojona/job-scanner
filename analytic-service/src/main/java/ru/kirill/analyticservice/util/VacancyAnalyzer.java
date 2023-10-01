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
    private int numberOfVacanciesWithMinSalary;
    private int numberOfVacanciesWithMaxSalary;
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
                if (salary.getFrom() != null) {
                    numberOfVacanciesWithMinSalary++;
                    averageMinSalary += (salary.getFrom() - averageMinSalary) / numberOfVacanciesWithMinSalary;
                }
                if (salary.getTo() != null) {
                    numberOfVacanciesWithMaxSalary++;
                    averageMaxSalary += (salary.getTo() - averageMaxSalary) / numberOfVacanciesWithMaxSalary;
                }
            }
        }
    }

}
