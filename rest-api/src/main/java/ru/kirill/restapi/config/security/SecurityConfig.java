package ru.kirill.restapi.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.http.entity.ContentType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import ru.kirill.commondto.response.ExceptionResponse;

import static org.springframework.security.config.Customizer.withDefaults;
import static ru.kirill.restapi.util.ResponseUtil.createExceptionResponse;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final ObjectMapper objectMapper;

    @Bean
    @SneakyThrows
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationEntryPoint authenticationEntryPoint,
                                           AccessDeniedHandler accessDeniedHandler) {
        return http
                .csrf(CsrfConfigurer::disable)
                .cors(withDefaults())
                .authorizeHttpRequests(config -> config
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/webjars/**"
                        ).permitAll()
                        .requestMatchers(HttpMethod.POST, "/member").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults())
                .exceptionHandling(config -> config
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler)
                )
                .build();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, e) -> {
            int statusCode = HttpStatus.UNAUTHORIZED.value();
            setResponse(request, response, e, statusCode);
        };
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, e) -> {
            int statusCode = HttpStatus.FORBIDDEN.value();
            setResponse(request, response, e, statusCode);
        };
    }

    @SneakyThrows
    public void setResponse(HttpServletRequest request, HttpServletResponse response, Exception e, int statusCode) {
        ExceptionResponse exceptionResponse = createExceptionResponse(e, request, statusCode);
        response.setContentType(ContentType.APPLICATION_JSON.toString());
        response.setStatus(statusCode);
        response.getWriter().write(objectMapper.writeValueAsString(exceptionResponse));
    }
}
