package ru.kirill.commondto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Data
@Schema(description = "Subscription")
public class SubscriptionResponse {

    @Schema(description = "Query", example = "Java developer")
    private String text;

    @Schema(description = "Member id", example = "1")
    private List<Long> memberIds;
}
