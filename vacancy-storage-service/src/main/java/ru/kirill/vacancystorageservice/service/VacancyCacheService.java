package ru.kirill.vacancystorageservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.kirill.vacancystorageservice.entity.VacancyRedis;
import ru.kirill.vacancystorageservice.repository.VacancyRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VacancyCacheService {

    private final VacancyRepository vacancyRepository;

    @Cacheable(cacheNames = "vacancy", key = "#id")
    public Optional<VacancyRedis> findVacancy(String id) {
        return vacancyRepository.findById(id);
    }

    @CacheEvict(cacheNames = "vacancy", key = "#vacancyRedis.id")
    public void saveOrUpdate(VacancyRedis vacancyRedis) {
        vacancyRepository.save(vacancyRedis);
    }
}
