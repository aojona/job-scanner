package ru.kirill.commondto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Подписка")
public class SubscriptionRequest {

    @Schema(description = "Профессия", example = "Java разработчик")
    private String text;

    @Schema(description = "id пользователя", example = "1")
    private Long memberId;
}
