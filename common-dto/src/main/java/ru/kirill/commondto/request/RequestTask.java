package ru.kirill.commondto.request;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class RequestTask implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private List<Long> chatIds;
    private QueryParams queryParams;

    @Data
    public static class QueryParams {

        private String text;
        private Integer perPage;
        private String orderBy;
    }
}