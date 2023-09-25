package ru.kirill.commondto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Join/Login member")
public class JwtResponse {

    @Schema(description = "Username", example = "JohnWick")
    private String username;

    @Schema(description = "JWT access token", example = "***")
    private String accessToken;
}
