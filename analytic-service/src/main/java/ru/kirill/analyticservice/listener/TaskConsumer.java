package ru.kirill.analyticservice.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.kirill.commondto.request.AnalyticsTask;

@Component
@RabbitListener(queues = "${rabbitmq.listen-queue}")
public class TaskConsumer {

    @RabbitHandler
    public void handleTask(AnalyticsTask task) {
    }
}
