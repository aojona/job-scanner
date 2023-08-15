package ru.kirill.vacancystorageservice.listener;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import ru.kirill.commondto.response.Vacancy;
import ru.kirill.vacancystorageservice.service.VacancyService;

@Component
@RequiredArgsConstructor
@RabbitListener(queues = "${rabbitmq.listen-queue}")
public class VacancyConsumer {

    private final VacancyService vacancyService;
    private final RabbitTemplate template;

    @SneakyThrows
    @RabbitHandler
    public void handleSearchMessageTask(Vacancy vacancy) {
        if (vacancyService.get(vacancy.getId()).isEmpty()) {
            template.convertAndSend(vacancyService.saveOrUpdate(vacancy));
        } else {
            vacancyService.saveOrUpdate(vacancy);
        }
    }
}
