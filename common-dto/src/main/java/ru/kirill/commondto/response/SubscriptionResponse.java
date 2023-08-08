package ru.kirill.commondto.response;

import lombok.Data;

@Data
public class SubscriptionResponse {

    private String request;
    private Long memberId;
}
