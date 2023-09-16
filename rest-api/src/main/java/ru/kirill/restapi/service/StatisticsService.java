package ru.kirill.restapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.kirill.commondto.response.AverageVacancyStatistics;
import ru.kirill.restapi.entity.Subscription;
import ru.kirill.restapi.repository.StatisticsRepository;
import ru.kirill.restapi.repository.SubscriptionRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final SubscriptionRepository subscriptionRepository;
    private final StatisticsRepository statisticsRepository;

    public List<AverageVacancyStatistics> getStatisticsForRandomSubscriptions() {
        return statisticsRepository
                .findAverageStatisticsWhereQueryIn(subscriptionRepository
                        .findRandomSubscriptions(PageRequest.of(0,7))
                        .stream()
                        .map(Subscription::getText)
                        .toList()
                )
                .stream()
                .toList();
    }

}
