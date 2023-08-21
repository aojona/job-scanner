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

    @Column(name = "telegram_chat_id")
    private Long chatId;

    @OneToMany(mappedBy = "member")
    private List<Subscription> subscriptions;
}
