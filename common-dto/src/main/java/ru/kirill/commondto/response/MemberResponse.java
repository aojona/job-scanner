package ru.kirill.commondto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Сущность пользователя")
public class MemberResponse {

    @Schema(description = "Уникальное имя пользователя", example = "JohnWick")
    private String username;

    @Schema(description = "Роль пользователя", example = "USER")
    private String role;
}
