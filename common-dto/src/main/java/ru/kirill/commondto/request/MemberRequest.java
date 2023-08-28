package ru.kirill.commondto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Сущность пользователя")
public class MemberRequest {

    @Schema(description = "Уникальное имя пользователя", example = "JohnWick")
    private String username;

    @Schema(description = "Пароль", example = "BabaYaga1964")
    private String rawPassword;
}
