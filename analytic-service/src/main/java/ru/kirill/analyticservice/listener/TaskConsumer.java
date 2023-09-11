package ru.kirill.analyticservice.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.kirill.analyticservice.service.AnalyticsService;
import ru.kirill.analyticservice.service.StatisticsService;
import ru.kirill.analyticservice.util.VacancyAnalyzer;
import ru.kirill.commondto.request.AnalyticsTask;

@Component
@RequiredArgsConstructor
@RabbitListener(queues = "${rabbitmq.listen-queue}")
public class TaskConsumer {

    private final AnalyticsService analyticsService;
    private final StatisticsService statisticsService;

    @RabbitHandler
    public void handleTask(AnalyticsTask task) {
        VacancyAnalyzer vacancyAnalyzer = analyticsService.gatherStatistics(task.getQuery());
        statisticsService.save(vacancyAnalyzer);
    }
}
