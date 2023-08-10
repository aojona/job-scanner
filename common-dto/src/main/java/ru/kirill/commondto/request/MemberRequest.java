package ru.kirill.commondto.request;

import lombok.Data;

@Data
public class MemberRequest {

    private String username;
    private String password;
}
