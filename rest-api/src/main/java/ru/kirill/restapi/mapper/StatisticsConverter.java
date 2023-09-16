package ru.kirill.restapi.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.kirill.commondto.request.StatisticsRequest;
import ru.kirill.commondto.response.StatisticsResponse;
import ru.kirill.restapi.entity.VacancyStatistics;

@Component
public class StatisticsConverter extends Mapper<VacancyStatistics, StatisticsRequest, StatisticsResponse> {
    public StatisticsConverter(ModelMapper modelMapper) {
        super(modelMapper, VacancyStatistics.class, StatisticsRequest.class, StatisticsResponse.class);
    }
}
