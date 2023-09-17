package ru.kirill.restapi.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.kirill.commondto.request.SubscriptionRequest;
import ru.kirill.commondto.response.SubscriptionResponse;
import ru.kirill.restapi.entity.Member;
import ru.kirill.restapi.entity.Subscription;

@Component
public class SubscriptionConverter extends Mapper<Subscription, SubscriptionRequest, SubscriptionResponse> {
    public SubscriptionConverter(ModelMapper modelMapper) {
        super(modelMapper, Subscription.class, SubscriptionRequest.class, SubscriptionResponse.class);
    }

    @Override
    protected void mapToDto(Subscription source, SubscriptionResponse destination) {
        destination
                .setMemberIds(source
                        .getMembers()
                        .stream()
                        .map(Member::getId)
                        .toList()
                );
        super.mapToDto(source, destination);
    }
}
