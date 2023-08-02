package ru.kirill.vacancystorageservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.kirill.vacancystorageservice.config.RedisProperties;
import ru.kirill.vacancystorageservice.entity.VacancyRedis;
import ru.kirill.vacancystorageservice.repository.VacancyRepository;

@Service
@RequiredArgsConstructor
public class VacancyService {

    private final VacancyRepository vacancyRepository;
    private final RedisProperties redisProperties;

    @Cacheable(cacheNames = "vacancy", key = "#vacancyRedis.id")
    public VacancyRedis saveOrUpdate(VacancyRedis vacancyRedis) {
        vacancyRedis.setTimeToLive(redisProperties.getTimeToLive());
        return vacancyRepository.save(vacancyRedis);
    }
}
