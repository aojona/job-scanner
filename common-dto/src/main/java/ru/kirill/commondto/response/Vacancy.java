package ru.kirill.commondto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Vacancy extends BaseResponse {

    private String url;
    private Timestamp publishedAt;
    private Experience experience;
    private Employer employer;
    private Employment employment;
    private Salary salary;
    private Area area;
    private Type type;
    private Snippet snippet;
}