package ru.kirill.commondto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Подписка")
public class SubscriptionRequest {

    @Schema(description = "Профессия", example = "Java разработчик")
    @Size(min = 4, message = "{error.subscription.short}")
    @Size(max = 32, message = "{error.subscription.long}")
    private String text;

    @Schema(description = "id пользователя", example = "1")
    private Long memberId;
}
