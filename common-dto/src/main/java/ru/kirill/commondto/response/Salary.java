package ru.kirill.commondto.response;

import lombok.Data;

@Data
public class Salary {

    private Integer from;
    private Integer to;
    private String currency;
    private Boolean gross;
}
