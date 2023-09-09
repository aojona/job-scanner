package ru.kirill.schedulerservice.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.kirill.commondto.request.RequestTask;
import ru.kirill.schedulerservice.entity.Member;
import ru.kirill.schedulerservice.entity.Subscription;
import java.util.Objects;

@Component
public class SubscriptionMapper extends Mapper<Subscription, RequestTask> {
    public SubscriptionMapper(ModelMapper modelMapper) {
        super(modelMapper, Subscription.class, RequestTask.class);
    }

    @Override
    protected void mapToDto(Subscription source, RequestTask destination) {
        destination.setChatIds(source
                .getMembers()
                .stream()
                .map(Member::getChatId)
                .filter(Objects::nonNull)
                .toList()
        );
    }
}
