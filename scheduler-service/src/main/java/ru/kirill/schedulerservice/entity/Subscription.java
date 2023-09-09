package ru.kirill.schedulerservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String text;

    @ManyToMany(mappedBy = "subscriptions")
    private List<Member> members = new ArrayList<>();
}
