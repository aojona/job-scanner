package ru.kirill.commondto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Подписка")
public class ShortSubResponse {

    @Schema(description = "Профессия", example = "Java разработчик")
    private String text;
}
