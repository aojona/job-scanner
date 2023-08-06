package ru.kirill.commondto.request;

import lombok.Data;

@Data
public class MemberRequest {

    private String login;
    private String password;
}
