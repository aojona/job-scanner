package ru.kirill.analyticservice.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kirill.analyticservice.entity.VacancyStatistics;
import ru.kirill.analyticservice.repository.StatisticsRepository;
import ru.kirill.analyticservice.util.VacancyAnalyzer;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final StatisticsRepository statisticsRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public void save(VacancyAnalyzer analyzer) {
        VacancyStatistics vacancyStatistics = statisticsRepository
                .findByQueryAndDate(analyzer.getQuery(), analyzer.getDate())
                .map(statistics -> {
                    modelMapper.map(analyzer, statistics);
                    return statistics;
                })
                .orElse(modelMapper.map(analyzer, VacancyStatistics.class));
        statisticsRepository.save(vacancyStatistics);
    }
}
