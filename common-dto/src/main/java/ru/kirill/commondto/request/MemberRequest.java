package ru.kirill.commondto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Сущность пользователя")
public class MemberRequest {

    @Size(min = 4, message = "{error.username.short}")
    @Size(max = 32, message = "{error.username.long}")
    @Schema(description = "Уникальное имя пользователя", example = "JohnWick")
    private String username;

    @Size(max = 32, message = "{error.password.long}")
    @Pattern(regexp = "^.*\\d+.*$", message = "{error.password.digit}")
    @Schema(description = "Пароль", example = "BabaYaga1964")
    private String rawPassword;
}
