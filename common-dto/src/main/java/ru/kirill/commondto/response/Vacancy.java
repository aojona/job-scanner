package ru.kirill.commondto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import ru.kirill.commondto.jackson.JsonZonedDateTime;
import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Vacancy implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String alternateUrl;
    @JsonZonedDateTime
    private ZonedDateTime publishedAt;
    private Experience experience;
    private Employer employer;
    private Employment employment;
    private Salary salary;
    private Area area;
    private Type type;
    private Snippet snippet;
}