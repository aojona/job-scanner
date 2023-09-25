package ru.kirill.commondto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Subscription")
public class SubscriptionRequest {

    @Schema(description = "Query", example = "Java developer")
    @Size(min = 4, message = "{error.subscription.short}")
    @Size(max = 32, message = "{error.subscription.long}")
    private String text;

    @Schema(description = "member id", example = "1")
    private Long memberId;
}
