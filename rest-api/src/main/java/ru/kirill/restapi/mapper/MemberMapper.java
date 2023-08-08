package ru.kirill.restapi.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.kirill.commondto.request.MemberRequest;
import ru.kirill.commondto.response.MemberResponse;
import ru.kirill.restapi.entity.Member;

@Component
public class MemberMapper extends Mapper<Member, MemberRequest, MemberResponse> {
    public MemberMapper(ModelMapper modelMapper) {
        super(modelMapper, Member.class, MemberRequest.class, MemberResponse.class);
    }
}
