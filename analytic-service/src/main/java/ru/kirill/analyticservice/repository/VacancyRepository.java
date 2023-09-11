package ru.kirill.analyticservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import ru.kirill.analyticservice.entity.VacancyRedis;

import java.time.LocalDate;

public interface VacancyRepository extends KeyValueRepository<VacancyRedis, String> {

    Page<VacancyRedis> findByPublishedAtAndQueries(LocalDate localDate, String query, Pageable pageable);
}
