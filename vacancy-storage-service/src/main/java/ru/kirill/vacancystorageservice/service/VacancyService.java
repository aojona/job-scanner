package ru.kirill.vacancystorageservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kirill.commondto.response.VacancyResponse;
import ru.kirill.vacancystorageservice.config.RedisProperties;
import ru.kirill.vacancystorageservice.entity.VacancyRedis;
import ru.kirill.vacancystorageservice.mapper.VacancyMapper;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VacancyService {

    private final RedisProperties redisProperties;
    private final VacancyMapper vacancyMapper;
    private final VacancyCacheService vacancyCacheService;

    public boolean saveOrUpdate(VacancyResponse vacancyResponse) {
        Optional<VacancyRedis> vacancyInRedis = vacancyCacheService.findVacancy(vacancyResponse.getVacancy().getId());
        String queryText = vacancyResponse.getQueryText();
        VacancyRedis vacancyRedis;
        if (vacancyInRedis.isPresent()) {
            vacancyRedis = vacancyInRedis.get();
            if (vacancyRedis.isContainQuery(queryText)) {
                return false;
            }
        } else {
            vacancyRedis = vacancyMapper.toEntity(vacancyResponse.getVacancy());
            vacancyRedis.setTimeToLive(redisProperties.getTimeToLive());
        }
        vacancyRedis.addQuery(queryText);
        vacancyCacheService.saveOrUpdate(vacancyRedis);
        return true;
    }
}
