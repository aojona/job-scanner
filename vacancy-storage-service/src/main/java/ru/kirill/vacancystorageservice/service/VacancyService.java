package ru.kirill.vacancystorageservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kirill.vacancystorageservice.entity.VacancyRedis;
import ru.kirill.vacancystorageservice.repository.VacancyRepository;

@Service
@RequiredArgsConstructor
public class VacancyService {

    private final VacancyRepository vacancyRepository;

    public VacancyRedis saveOrUpdate(VacancyRedis vacancyRedis) {
        return vacancyRepository.save(vacancyRedis);
    }
}
