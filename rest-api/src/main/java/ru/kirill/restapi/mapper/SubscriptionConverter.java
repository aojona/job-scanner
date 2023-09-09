package ru.kirill.restapi.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.kirill.commondto.request.SubscriptionRequest;
import ru.kirill.commondto.response.SubscriptionResponse;
import ru.kirill.restapi.entity.Subscription;

@Component
public class SubscriptionConverter extends Mapper<Subscription, SubscriptionRequest, SubscriptionResponse> {
    public SubscriptionConverter(ModelMapper modelMapper) {
        super(modelMapper, Subscription.class, SubscriptionRequest.class, SubscriptionResponse.class);
    }
}
