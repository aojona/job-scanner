package ru.kirill.analyticservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kirill.analyticservice.entity.VacancyStatistics;

public interface StatisticsRepository extends JpaRepository<VacancyStatistics, Long> {
}
