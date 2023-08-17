package ru.kirill.schedulerservice.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.kirill.commondto.response.SubscriptionResponse;
import ru.kirill.schedulerservice.entity.Subscription;

@Component
public class SubscriptionMapper extends Mapper<Subscription, SubscriptionResponse> {
    public SubscriptionMapper(ModelMapper modelMapper) {
        super(modelMapper, SubscriptionResponse.class);
    }
}
