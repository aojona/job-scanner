package ru.kirill.commondto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "Member")
public class MemberResponse {

    @Schema(description = "Username", example = "JohnWick")
    private String username;

    @Schema(description = "Role", example = "USER")
    private String role;

    @Schema(description = "Telegram chat id")
    private Long telegramChatId;

    @Schema(description = "Subscriptions")
    private List<ShortSubResponse> subscriptions;
}
