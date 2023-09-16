package ru.kirill.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kirill.commondto.response.AverageVacancyStatistics;
import ru.kirill.restapi.entity.VacancyStatistics;

import java.time.LocalDate;
import java.util.List;

public interface StatisticsRepository extends JpaRepository<VacancyStatistics, Long> {

    @Query("""
            SELECT new ru.kirill.commondto.response.AverageVacancyStatistics(
                s.query,
                AVG(s.averageMinSalary),
                AVG(s.averageMaxSalary),
                SUM(s.numberOfVacancies),
                SUM(s.numberOfVacanciesWithSalary)
            )
            FROM VacancyStatistics s
            WHERE s.query IN :queries
            GROUP BY s.query
            """)
    List<AverageVacancyStatistics> findAverageStatisticsWhereQueryIn(List<String> queries);
    List<VacancyStatistics> findByQueryAndDateAfterOrderByDateDesc(String query, LocalDate date);

}