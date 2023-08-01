package ru.kirill.vacancyscanservice.listener;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import ru.kirill.commondto.request.SearchMessage;
import ru.kirill.commondto.response.VacancyPage;
import ru.kirill.vacancyscanservice.annotation.Consumer;
import ru.kirill.vacancyscanservice.client.SearchClient;

@Consumer
@RequiredArgsConstructor
@RabbitListener(queues = "${rabbitmq.scheduled-task-queue}")
public class TaskConsumer {

    private final SearchClient searchClient;
    private final RabbitTemplate template;

    @SneakyThrows
    @RabbitHandler
    public void handleSearchMessageTask(SearchMessage searchMessage) {
        VacancyPage vacancyPage = searchClient
                .searchVacancies(searchMessage);

        System.out.println(searchMessage);
        System.out.println(vacancyPage.getPerPage());


        vacancyPage
                .getVacancies()
                .forEach(template::convertAndSend);
    }
}
