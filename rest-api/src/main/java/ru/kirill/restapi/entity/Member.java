package ru.kirill.restapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import ru.kirill.restapi.enums.Role;
import java.util.List;

@Data
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    private String password;

    private Long telegramChatId;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "member")
    private List<Subscription> subscriptions;
}
