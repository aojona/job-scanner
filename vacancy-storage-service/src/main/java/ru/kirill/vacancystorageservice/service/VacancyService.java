package ru.kirill.vacancystorageservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.kirill.commondto.response.Vacancy;
import ru.kirill.vacancystorageservice.config.RedisProperties;
import ru.kirill.vacancystorageservice.mapper.VacancyMapper;
import ru.kirill.vacancystorageservice.repository.VacancyRepository;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VacancyService {

    private final VacancyRepository vacancyRepository;
    private final RedisProperties redisProperties;
    private final VacancyMapper vacancyMapper;

    @CachePut(value = "vacancy", key = "#vacancy.id")
    public Vacancy saveOrUpdate(Vacancy vacancy) {
        return Optional
                .of(vacancy)
                .map(vacancyMapper::toEntity)
                .map(vacancyRedis -> {
                    vacancyRedis.setTimeToLive(redisProperties.getTimeToLive());
                    return vacancyRepository.save(vacancyRedis);
                })
                .map(vacancyMapper::toDto)
                .orElseThrow();
    }

    @Cacheable(cacheNames = "vacancy", key = "#id")
    public Optional<Vacancy> get(String id) {
        return vacancyRepository
                .findById(id)
                .map(vacancyMapper::toDto);
    }
}
