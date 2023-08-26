package ru.kirill.restapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kirill.commondto.request.MemberRequest;
import ru.kirill.commondto.request.PageableRequest;
import ru.kirill.commondto.response.MemberResponse;
import ru.kirill.restapi.security.SecurityUser;
import ru.kirill.restapi.entity.Member;
import ru.kirill.restapi.exception.MemberNotFoundException;
import ru.kirill.restapi.mapper.MemberMapper;
import ru.kirill.restapi.repository.MemberRepository;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    public MemberResponse get(long id) {
        return memberRepository
                .findById(id)
                .map(memberMapper::toDto)
                .orElseThrow(() -> new MemberNotFoundException(id));
    }

    public Page<MemberResponse> getAll(PageableRequest pageable) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        return memberRepository
                .findAll(pageRequest)
                .map(memberMapper::toDto);
    }

    @Transactional
    public MemberResponse create(MemberRequest memberRequest) {
        return Optional
                .of(memberRequest)
                .map(memberMapper::toEntity)
                .map(memberRepository::save)
                .map(memberMapper::toDto)
                .orElseThrow();
    }

    @Transactional
    public MemberResponse update(long id, MemberRequest memberRequest) {
        return memberRepository
                .findById(id)
                .map(entity -> {
                    memberMapper.updateEntity(memberRequest, entity);
                    return memberRepository.save(entity);
                })
                .map(memberMapper::toDto)
                .orElseThrow(() -> new MemberNotFoundException(id));
    }

    @Transactional
    public void delete(long id) {
        Member member = memberRepository
                .findById(id)
                .orElseThrow(() -> new MemberNotFoundException(id));
        memberRepository.delete(member);
    }

    @Override
    public SecurityUser loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository
                .findByUsername(username)
                .map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user: " + username));
    }
}
