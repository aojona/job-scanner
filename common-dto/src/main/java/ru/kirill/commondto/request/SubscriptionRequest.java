package ru.kirill.commondto.request;

import lombok.Data;

@Data
public class SubscriptionRequest {

    private String request;
    private Long memberId;
}
