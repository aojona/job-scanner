package ru.kirill.restapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kirill.commondto.request.PageableRequest;
import ru.kirill.commondto.request.SubscriptionRequest;
import ru.kirill.commondto.response.SubscriptionResponse;
import ru.kirill.restapi.entity.Subscription;
import ru.kirill.restapi.exception.MemberNotFoundException;
import ru.kirill.restapi.exception.SubscriptionAlreadyExistException;
import ru.kirill.restapi.exception.SubscriptionNotFoundException;
import ru.kirill.restapi.mapper.SubscriptionConverter;
import ru.kirill.restapi.repository.MemberRepository;
import ru.kirill.restapi.repository.SubscriptionRepository;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionConverter subscriptionConverter;
    private final MemberRepository memberRepository;

    public SubscriptionResponse get(long id) {
        return subscriptionRepository
                .findById(id)
                .map(subscriptionConverter::toDto)
                .orElseThrow(() -> new SubscriptionNotFoundException(id));
    }

    public Page<SubscriptionResponse> getAll(PageableRequest pageable) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        return subscriptionRepository
                .findAll(pageRequest)
                .map(subscriptionConverter::toDto);
    }

    @Transactional
    public SubscriptionResponse create(SubscriptionRequest subscriptionRequest) {
        if (memberIsEmpty(subscriptionRequest.getMemberId())) {
            throw new MemberNotFoundException(subscriptionRequest.getMemberId());
        }
        try {
            return Optional
                    .of(subscriptionRequest)
                    .map(subscriptionConverter::toEntity)
                    .map(subscriptionRepository::save)
                    .map(subscriptionConverter::toDto)
                    .orElseThrow();
        } catch (DataAccessException e) {
            throw new SubscriptionAlreadyExistException();
        }

    }

    @Transactional
    public SubscriptionResponse update(long id, SubscriptionRequest subscriptionRequest) {
        if (memberIsEmpty(subscriptionRequest.getMemberId())) {
            throw new MemberNotFoundException(subscriptionRequest.getMemberId());
        }
        return subscriptionRepository
                .findById(id)
                .map(entity -> {
                    subscriptionConverter.updateEntity(subscriptionRequest, entity);
                    return subscriptionRepository.save(entity);
                })
                .map(subscriptionConverter::toDto)
                .orElseThrow(() -> new SubscriptionNotFoundException(id));
    }

    @Transactional
    public void delete(long id) {
        Subscription subscription = subscriptionRepository
                .findById(id)
                .orElseThrow(() -> new SubscriptionNotFoundException(id));
        subscriptionRepository.delete(subscription);
    }

    @Transactional
    public void delete(long memberId, String text) {
        subscriptionRepository.deleteByMemberIdAndText(memberId, text);
    }

    private boolean memberIsEmpty(long memberId) {
        return memberRepository
                .findById(memberId)
                .isEmpty();
    }
}
