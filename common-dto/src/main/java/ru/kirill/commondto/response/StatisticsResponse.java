package ru.kirill.commondto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(description = "Vacancy statistics")
public class StatisticsResponse {

    @Schema(description = "Query", example = "Java developer")
    private String query;

    @Schema(description = "Number of new vacancies", example = "100")
    private int numberOfVacancies;

    @Schema(description = "Number of new vacancies with salary", example = "50")
    private int numberOfVacanciesWithSalary;

    @Schema(description = "Average salary lower bound", example = "1000")
    private double averageMinSalary;

    @Schema(description = "Average salary upper bound", example = "2000")
    private double averageMaxSalary;

    @Schema(description = "Job opening date", example = "2023-10-10")
    private LocalDate date;
}
