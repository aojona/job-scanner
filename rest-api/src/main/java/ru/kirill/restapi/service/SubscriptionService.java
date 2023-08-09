package ru.kirill.restapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kirill.commondto.request.SubscriptionRequest;
import ru.kirill.commondto.response.SubscriptionResponse;
import ru.kirill.restapi.entity.Subscription;
import ru.kirill.restapi.exception.MemberNotFoundException;
import ru.kirill.restapi.exception.SubscriptionNotFoundException;
import ru.kirill.restapi.mapper.SubscriptionMapper;
import ru.kirill.restapi.repository.MemberRepository;
import ru.kirill.restapi.repository.SubscriptionRepository;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;
    private final MemberRepository memberRepository;

    public SubscriptionResponse get(long id) {
        return subscriptionRepository
                .findById(id)
                .map(subscriptionMapper::toDto)
                .orElseThrow(() -> new SubscriptionNotFoundException(id));
    }

    public Page<SubscriptionResponse> getAll(Pageable pageable) {
        return subscriptionRepository
                .findAll(pageable)
                .map(subscriptionMapper::toDto);
    }

    @Transactional
    public SubscriptionResponse create(SubscriptionRequest subscriptionRequest) {
        if (memberIsEmpty(subscriptionRequest.getMemberId())) {
            throw new MemberNotFoundException(subscriptionRequest.getMemberId());
        }
        return Optional
                .of(subscriptionRequest)
                .map(subscriptionMapper::toEntity)
                .map(subscriptionRepository::save)
                .map(subscriptionMapper::toDto)
                .orElseThrow();
    }

    @Transactional
    public SubscriptionResponse update(long id, SubscriptionRequest subscriptionRequest) {
        if (memberIsEmpty(subscriptionRequest.getMemberId())) {
            throw new MemberNotFoundException(subscriptionRequest.getMemberId());
        }
        return subscriptionRepository
                .findById(id)
                .map(entity -> {
                    subscriptionMapper.updateEntity(subscriptionRequest, entity);
                    return subscriptionRepository.save(entity);
                })
                .map(subscriptionMapper::toDto)
                .orElseThrow(() -> new SubscriptionNotFoundException(id));
    }

    @Transactional
    public void delete(long id) {
        Subscription subscription = subscriptionRepository
                .findById(id)
                .orElseThrow(() -> new SubscriptionNotFoundException(id));
        subscriptionRepository.delete(subscription);
    }

    private boolean memberIsEmpty(long memberId) {
        return memberRepository
                .findById(memberId)
                .isEmpty();
    }
}