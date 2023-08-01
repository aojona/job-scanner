package ru.kirill.vacancystorageservice.listener;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import ru.kirill.commondto.response.Vacancy;
import ru.kirill.vacancystorageservice.annotation.Consumer;
import ru.kirill.vacancystorageservice.entity.VacancyRedis;
import ru.kirill.vacancystorageservice.service.VacancyService;

@Consumer
@RequiredArgsConstructor
@RabbitListener(queues = "${rabbitmq.vacancy-storage-queue}")
public class VacancyConsumer {

    private final VacancyService vacancyService;
    private final ModelMapper modelMapper;

    @SneakyThrows
    @RabbitHandler
    public void handleSearchMessageTask(Vacancy vacancy) {
        VacancyRedis vacancyRedis = modelMapper.map(vacancy, VacancyRedis.class);
        VacancyRedis save = vacancyService.saveOrUpdate(vacancyRedis);
    }
}
