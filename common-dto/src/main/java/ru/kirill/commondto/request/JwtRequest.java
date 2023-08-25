package ru.kirill.commondto.request;

import lombok.Data;

@Data
public class JwtRequest {

    private String username;
    private String password;
}
