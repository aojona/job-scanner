package ru.kirill.analyticservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kirill.analyticservice.entity.VacancyStatistics;

import java.time.LocalDate;
import java.util.Optional;

public interface StatisticsRepository extends JpaRepository<VacancyStatistics, Long> {

    Optional<VacancyStatistics> findByQueryAndDate(String query, LocalDate date);
}
