package ru.kirill.commondto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Employment implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
}
