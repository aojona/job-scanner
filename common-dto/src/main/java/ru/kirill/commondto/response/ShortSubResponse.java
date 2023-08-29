package ru.kirill.commondto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Подписка")
public class ShortSubResponse {

    @Schema(description = "Профессия", example = "Java разработчик")
    private String text;
}
