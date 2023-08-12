package ru.kirill.commondto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Подписка")
public class SubscriptionResponse {

    @Schema(description = "Профессия", example = "Java разработчик")
    private String request;

    @Schema(description = "id пользователя", example = "1")
    private Long memberId;
}
