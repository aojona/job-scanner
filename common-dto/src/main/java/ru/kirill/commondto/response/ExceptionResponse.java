package ru.kirill.commondto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kirill.commondto.jackson.JsonLocalDateTime;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {

    @JsonLocalDateTime
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String path;
}
