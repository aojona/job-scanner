package ru.kirill.commondto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchMessage implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String text;
    private int perPage;
    private int page;
}
