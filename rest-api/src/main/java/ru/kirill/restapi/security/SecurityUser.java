package ru.kirill.restapi.security;

import lombok.Getter;
import org.springframework.security.core.userdetails.User;
import ru.kirill.restapi.entity.Member;
import java.util.Collections;

@Getter
public class SecurityUser extends User {

    private final Long id;

    public SecurityUser(Member user) {
        super(user.getUsername(), user.getPassword(), Collections.singleton(user.getRole()));
        this.id = user.getId();
    }
}
