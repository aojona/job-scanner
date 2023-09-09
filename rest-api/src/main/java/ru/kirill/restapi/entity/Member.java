package ru.kirill.restapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import ru.kirill.restapi.enums.Role;
import java.util.*;

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

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "member_subscription",
            joinColumns = @JoinColumn(name = "member_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "subscription_id", referencedColumnName = "id")
    )
    @OrderBy("text")
    private Set<Subscription> subscriptions = new HashSet<>();

    public void addSubscription(Subscription subscription) {
        subscriptions.add(subscription);
        subscription.getMembers().add(this);
    }

    public void removeSubscription(Subscription subscription) {
        subscriptions.remove(subscription);
        subscription.getMembers().remove(this);
    }
}
