package ru.kirill.schedulerservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String telegramChatId;

    @OneToMany(mappedBy = "member")
    private List<Subscription> subscriptions;
}
