package ru.kirill.commondto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Salary implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String currency;
    private Integer from;
    private Integer to;
    private Boolean gross;
}
