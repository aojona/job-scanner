package ru.kirill.vacancynotifierservice.listener;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.kirill.commondto.response.VacancyResponse;

@Component
@RequiredArgsConstructor
@RabbitListener(queues = "${rabbitmq.listen-queue}")
public class VacancyConsumer {

    @RabbitHandler
    public void handleMessage(VacancyResponse vacancyResponse) {
    }
}
