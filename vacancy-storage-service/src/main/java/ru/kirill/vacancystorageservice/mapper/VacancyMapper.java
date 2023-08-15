package ru.kirill.vacancystorageservice.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.kirill.commondto.response.Vacancy;
import ru.kirill.vacancystorageservice.entity.VacancyRedis;

@Component
@RequiredArgsConstructor
public class VacancyMapper {

    private final ModelMapper modelMapper;

    public Vacancy toDto(VacancyRedis vacancyRedis) {
        return modelMapper.map(vacancyRedis, Vacancy.class);
    }

    public VacancyRedis toEntity(Vacancy vacancy) {
        return modelMapper.map(vacancy, VacancyRedis.class);
    }
}
