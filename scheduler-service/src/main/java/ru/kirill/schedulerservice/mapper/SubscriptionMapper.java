package ru.kirill.schedulerservice.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.kirill.commondto.request.RequestTask;
import ru.kirill.schedulerservice.entity.Subscription;

@Component
public class SubscriptionMapper extends Mapper<Subscription, RequestTask> {
    public SubscriptionMapper(ModelMapper modelMapper) {
        super(modelMapper, RequestTask.class);
    }
}
