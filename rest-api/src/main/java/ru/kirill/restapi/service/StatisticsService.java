package ru.kirill.restapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kirill.commondto.response.AverageVacancyStatistics;
import ru.kirill.commondto.response.StatisticsResponse;
import ru.kirill.restapi.config.SearchProperties;
import ru.kirill.restapi.entity.Subscription;
import ru.kirill.restapi.exception.MemberNotFoundException;
import ru.kirill.restapi.mapper.StatisticsConverter;
import ru.kirill.restapi.repository.MemberRepository;
import ru.kirill.restapi.repository.StatisticsRepository;
import ru.kirill.restapi.repository.SubscriptionRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StatisticsService {

    private final SubscriptionRepository subscriptionRepository;
    private final StatisticsRepository statisticsRepository;
    private final MemberRepository memberRepository;
    private final StatisticsConverter statisticsConverter;
    private final SearchProperties searchProperties;

    public List<AverageVacancyStatistics> getStatisticsForRandomSubscriptions() {
        return statisticsRepository
                .findAverageStatisticsWhereQueryIn(subscriptionRepository
                        .findRandomSubscriptions(
                                searchProperties.maxSubscriptionLength(),
                                PageRequest.of(searchProperties.page(), searchProperties.size()
                        ))
                        .stream()
                        .map(Subscription::getText)
                        .toList()
                )
                .stream()
                .toList();
    }

    public Map<String, List<StatisticsResponse>> getStatisticForMemberSubscriptions(Long id) {
        LocalDate limitDate = LocalDate.now().minusDays(searchProperties.range());
        return memberRepository
                .findMemberWithSubscriptionsById(id)
                .orElseThrow(() -> new MemberNotFoundException(id))
                .getSubscriptions()
                .stream()
                .map(Subscription::getText)
                .collect(Collectors.toMap(
                        Function.identity(),
                        query -> statisticsRepository
                                .findByQueryAndDateAfterOrderByDateDesc(query, limitDate)
                                .stream()
                                .map(statisticsConverter::toDto)
                                .toList()
                ));
    }

    @Transactional
    public void delete(String query) {
        statisticsRepository.deleteAllByQuery(query);
    }
}
