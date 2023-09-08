package ru.kirill.vacancystorageservice.mapper;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.kirill.commondto.response.Vacancy;
import ru.kirill.vacancystorageservice.entity.VacancyRedis;

@Component
@RequiredArgsConstructor
public class VacancyMapper {

    private final ModelMapper modelMapper;

    @PostConstruct
    public void init() {
        modelMapper
                .createTypeMap(Vacancy.class, VacancyRedis.class)
                .setPostConverter(context -> {
                    Vacancy source = context.getSource();
                    VacancyRedis destination = context.getDestination();
                    destination.setPublishedAt(source.getPublishedAt().toLocalDate());
                    return destination;
                });
    }

    public Vacancy toDto(VacancyRedis vacancyRedis) {
        return modelMapper.map(vacancyRedis, Vacancy.class);
    }

    public VacancyRedis toEntity(Vacancy vacancy) {
        return modelMapper.map(vacancy, VacancyRedis.class);
    }
}
