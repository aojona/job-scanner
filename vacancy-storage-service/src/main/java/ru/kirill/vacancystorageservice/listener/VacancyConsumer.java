package ru.kirill.vacancystorageservice.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import ru.kirill.commondto.response.VacancyResponse;
import ru.kirill.vacancystorageservice.service.VacancyService;

@Component
@RequiredArgsConstructor
@RabbitListener(queues = "${rabbitmq.listen-queue}")
public class VacancyConsumer {

    private final VacancyService vacancyService;
    private final RabbitTemplate template;
    private final DateAdviser dateAdviser;

    @RabbitHandler
    public void handleMessage(VacancyResponse vacancyResponse) {
        boolean isSavedOrUpdated = vacancyService.saveOrUpdate(vacancyResponse);
        boolean isNew = dateAdviser.isNew(vacancyResponse.getVacancy().getPublishedAt());
        if (isSavedOrUpdated && isNew) {
            template.convertAndSend(vacancyResponse);
        }
    }
}
