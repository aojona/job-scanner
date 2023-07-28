package ru.kirill.vacancyscanservice.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Vacancy extends BaseResponse {

    private String url;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private LocalDateTime publishedAt;
    private Experience experience;
    private Employer employer;
    private Employment employment;
    private Salary salary;
    private Area area;
    private Type type;
    private Snippet snippet;
}