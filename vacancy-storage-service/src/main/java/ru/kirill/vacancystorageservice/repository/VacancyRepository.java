package ru.kirill.vacancystorageservice.repository;

import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;
import ru.kirill.vacancystorageservice.entity.VacancyRedis;

@Repository
public interface VacancyRepository extends KeyValueRepository<VacancyRedis,String> {
}
