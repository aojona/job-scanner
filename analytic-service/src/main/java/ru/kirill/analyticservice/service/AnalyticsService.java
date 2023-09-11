package ru.kirill.analyticservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ru.kirill.analyticservice.entity.VacancyRedis;
import ru.kirill.analyticservice.util.VacancyAnalyzer;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

    private final VacancyService vacancyService;

    public VacancyAnalyzer gatherStatistics(String query) {
        LocalDate currentDate = LocalDate.now();
        VacancyAnalyzer analyzer = new VacancyAnalyzer(query);
        Page<VacancyRedis> vacancies = vacancyService.findVacancies(currentDate, query);
        analyzeData(analyzer, vacancies);
        while (vacancies.hasNext()) {
            vacancies = vacancyService.findVacancies(currentDate, query, vacancies.nextPageable());
            analyzeData(analyzer, vacancies);
        }
        return analyzer;
    }

    private void analyzeData(VacancyAnalyzer analyzer, Page<VacancyRedis> vacancies) {
        vacancies
                .stream()
                .map(VacancyRedis::getSalary)
                .forEach(analyzer::analyze);
    }
}
