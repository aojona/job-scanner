package ru.kirill.analyticservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.kirill.analyticservice.entity.VacancyRedis;
import ru.kirill.analyticservice.repository.VacancyRepository;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class VacancyService {

    private final VacancyRepository vacancyRepository;

    @Value("${page-request.batch-size}")
    private int batchSize;

    public Page<VacancyRedis> findVacancies(LocalDate localDate, String query) {
        return vacancyRepository.findByPublishedAtAndQueries(localDate, query, PageRequest.of(0, batchSize));
    }

    public Page<VacancyRedis> findVacancies(LocalDate localDate, String query, Pageable pageable) {
        return vacancyRepository.findByPublishedAtAndQueries(localDate, query, pageable);
    }
}
