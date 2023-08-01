package ru.kirill.vacancystorageservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.kirill.vacancystorageservice.entity.VacancyRedis;
import ru.kirill.vacancystorageservice.repository.VacancyRepository;

@Service
@RequiredArgsConstructor
public class VacancyService {

    private final VacancyRepository vacancyRepository;

    @Cacheable(cacheNames = "vacancy", key = "#vacancyRedis.id")
    public VacancyRedis saveOrUpdate(VacancyRedis vacancyRedis) {
        System.out.println("saveOrUpdate:" + vacancyRedis.getId());
        return vacancyRepository.save(vacancyRedis);
    }
}
