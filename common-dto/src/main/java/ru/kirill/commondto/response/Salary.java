package ru.kirill.commondto.response;

import lombok.Data;
import java.io.Serial;
import java.io.Serializable;

@Data
public class Salary implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer from;
    private Integer to;
    private String currency;
    private Boolean gross;
}
