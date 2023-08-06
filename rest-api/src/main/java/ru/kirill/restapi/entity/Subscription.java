package ru.kirill.restapi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String request;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
}
