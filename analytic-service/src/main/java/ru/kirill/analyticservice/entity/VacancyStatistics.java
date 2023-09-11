package ru.kirill.analyticservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class VacancyStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String query;
    private LocalDate date;
    private double averageMinSalary;
    private double averageMaxSalary;
    private int numberOfVacancies;
    private int numberOfVacanciesWithSalary;
}
