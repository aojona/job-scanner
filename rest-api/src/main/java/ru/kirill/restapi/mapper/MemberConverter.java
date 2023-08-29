package ru.kirill.restapi.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kirill.commondto.request.MemberRequest;
import ru.kirill.commondto.response.MemberResponse;
import ru.kirill.restapi.entity.Member;
import ru.kirill.restapi.enums.Role;

import java.util.Optional;

@Component
public class MemberConverter extends Mapper<Member, MemberRequest, MemberResponse> {

    private final PasswordEncoder passwordEncoder;

    public MemberConverter(ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        super(modelMapper, Member.class, MemberRequest.class, MemberResponse.class);
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void mapToEntity(MemberRequest source, Member destination) {
        destination.setRole(Role.USER);
        Optional
                .of(source.getRawPassword())
                .map(passwordEncoder::encode)
                .ifPresent(destination::setPassword);
    }
}
