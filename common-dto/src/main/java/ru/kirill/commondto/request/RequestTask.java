package ru.kirill.commondto.request;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class RequestTask implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String chatId;
    private QueryParams queryParams;

    @Data
    public static class QueryParams {

        private String text;
        private Integer perPage;
        private String orderBy;
    }
}