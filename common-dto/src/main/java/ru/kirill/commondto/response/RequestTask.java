package ru.kirill.commondto.response;

import lombok.Data;

@Data
public class RequestTask {

    private Long memberId;
    private QueryParams queryParams;

    @Data
    public static class QueryParams {

        private String request;
    }
}