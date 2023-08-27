package ru.kirill.restapi.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.security.Principal;

@Getter
@AllArgsConstructor
public class JwtPrincipal implements Principal {

    private long id;
    private String username;

    @Override
    public String getName() {
        return username;
    }
}
