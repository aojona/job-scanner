package ru.kirill.commondto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Join/Login member")
public class JwtRequest {

    @Size(min = 4, message = "{error.username.short}")
    @Size(max = 32, message = "{error.username.long}")
    @Schema(description = "Username", example = "JohnWick")
    private String username;

    @Pattern(regexp = "^.*\\d+$", message = "{error.password.digit}")
    @Size(max = 32, message = "{error.password.long}")
    @Schema(description = "Password", example = "BabaYaga1964")
    private String password;
}
