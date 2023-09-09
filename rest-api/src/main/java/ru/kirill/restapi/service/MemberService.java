package ru.kirill.restapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kirill.commondto.request.MemberRequest;
import ru.kirill.commondto.request.PageableRequest;
import ru.kirill.commondto.response.MemberResponse;
import ru.kirill.restapi.entity.Subscription;
import ru.kirill.restapi.exception.MemberAlreadyExistException;
import ru.kirill.restapi.exception.SubscriptionNotFoundException;
import ru.kirill.restapi.repository.SubscriptionRepository;
import ru.kirill.restapi.security.SecurityUser;
import ru.kirill.restapi.entity.Member;
import ru.kirill.restapi.exception.MemberNotFoundException;
import ru.kirill.restapi.mapper.MemberConverter;
import ru.kirill.restapi.repository.MemberRepository;
import ru.kirill.restapi.util.EntityUtil;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final MemberConverter memberConverter;
    private final SubscriptionRepository subscriptionRepository;

    public MemberResponse get(long id) {
        return memberRepository
                .findMemberWithSubscriptionsById(id)
                .map(memberConverter::toDto)
                .orElseThrow(() -> new MemberNotFoundException(id));
    }

    public Page<MemberResponse> getAll(PageableRequest pageable) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        return memberRepository
                .findAll(pageRequest)
                .map(memberConverter::toDto);
    }

    @Transactional
    public MemberResponse create(MemberRequest memberRequest) {
        try {
            return Optional
                    .of(memberRequest)
                    .map(memberConverter::toEntity)
                    .map(memberRepository::save)
                    .map(memberConverter::toDto)
                    .orElseThrow();
        } catch (DataAccessException e) {
            throw new MemberAlreadyExistException();
        }

    }

    @Transactional
    public MemberResponse update(long id, MemberRequest memberRequest) {
        return memberRepository
                .findMemberWithSubscriptionsById(id)
                .map(entity -> {
                    memberConverter.updateEntity(memberRequest, entity);
                    return memberRepository.save(entity);
                })
                .map(memberConverter::toDto)
                .orElseThrow(() -> new MemberNotFoundException(id));
    }

    @Transactional
    public void delete(long id) {
        Member member = memberRepository
                .findMemberWithSubscriptionsById(id)
                .orElseThrow(() -> new MemberNotFoundException(id));
        memberRepository.delete(member);
    }

    @Transactional
    public void updateChatId(long memberId, Long telegramChatId) {
        memberRepository.updateChatId(memberId, telegramChatId);
    }

    @Override
    public SecurityUser loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository
                .findByUsername(username)
                .map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user: " + username));
    }

    @Transactional
    public void addSubscription(long id, String text) {
        Member updatedmember = Optional
                .of(memberRepository
                        .findMemberWithSubscriptionsById(id)
                        .orElseThrow(() -> new MemberNotFoundException(id))
                )
                .map(member -> {
                    Subscription subscription = subscriptionRepository
                            .findByText(text)
                            .orElse(EntityUtil.createSubscriptionWithText(text));
                    member.addSubscription(subscription);
                    return member;
                })
                .orElseThrow();
        memberRepository.save(updatedmember);
    }

    @Transactional
    public void removeSubscription(long id, String text) {
        Member member = memberRepository.findMemberWithSubscriptionsById(id).orElseThrow(() -> new MemberNotFoundException(id));
        Subscription subscription = subscriptionRepository.findByText(text).orElseThrow(() -> new SubscriptionNotFoundException(text));
        member.removeSubscription(subscription);
        memberRepository.save(member);
    }
}
