package ru.kirill.restapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "REST Api",
        description = "job-scanner",
        version = "1.0.0",
        contact = @Contact(name = "Kirill Gribtsov", email = "aojona@ya.ru", url = "https://github.com/aojona")
))
public class OpenApiConfig {
}
