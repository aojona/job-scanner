package ru.kirill.analyticservice.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.kirill.analyticservice.entity.VacancyStatistics;
import ru.kirill.analyticservice.repository.StatisticsRepository;
import ru.kirill.analyticservice.util.VacancyAnalyzer;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final StatisticsRepository statisticsRepository;
    private final ModelMapper modelMapper;

    public void save(VacancyAnalyzer analyzer) {
        statisticsRepository.save(modelMapper.map(analyzer, VacancyStatistics.class));
    }
}
