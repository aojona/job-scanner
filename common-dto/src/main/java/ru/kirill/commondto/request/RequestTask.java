package ru.kirill.commondto.request;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class RequestTask implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long memberId;
    private QueryParams queryParams;

    @Data
    public static class QueryParams {

        private String text;
    }
}