package ru.kirill.restapi.config.security;

import lombok.Getter;
import org.springframework.security.core.userdetails.User;
import ru.kirill.restapi.entity.Member;
import ru.kirill.restapi.enums.Role;
import java.util.Collections;

@Getter
public class SecurityUser extends User {

    private final Long id;

    public SecurityUser(Member user) {
        super(user.getUsername(), user.getPassword(), Collections.singleton(Role.USER));
        this.id = user.getId();
    }
}
