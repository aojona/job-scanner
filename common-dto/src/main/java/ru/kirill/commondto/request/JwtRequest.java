package ru.kirill.commondto.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class JwtRequest {

    @Size(min = 4, message = "{error.username.short}")
    @Size(max = 32, message = "{error.username.long}")
    private String username;

    @Pattern(regexp = "^.*\\d+$", message = "{error.password.digit}")
    @Size(max = 32, message = "{error.password.long}")
    private String password;
}
