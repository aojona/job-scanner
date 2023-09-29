package ru.kirill.commondto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Subscription")
public class SubscriptionRequest {

    @Schema(description = "Query", example = "Java developer")
    @Size(min = 3, message = "{error.subscription.short}")
    @Size(max = 32, message = "{error.subscription.long}")
    @Pattern(regexp = "^\\p{Lu}.*$", message = "{error.subscription.letter}")
    private String text;

    @Schema(description = "member id", example = "1")
    private Long memberId;
}
