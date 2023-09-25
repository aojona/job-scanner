package ru.kirill.commondto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Member")
public class MemberRequest {

    @Size(min = 4, message = "{error.username.short}")
    @Size(max = 32, message = "{error.username.long}")
    @Schema(description = "Username", example = "JohnWick")
    private String username;

    @Size(max = 32, message = "{error.password.long}")
    @Pattern(regexp = "^.*\\d+.*$", message = "{error.password.digit}")
    @Schema(description = "Password", example = "BabaYaga1964")
    private String rawPassword;
}
