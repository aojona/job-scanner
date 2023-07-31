package ru.kirill.commondto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchMessage {

    private String text;
    private int perPage;
    private int page;
}
