package ru.kirill.restapi.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.http.entity.ContentType;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.kirill.commondto.response.ExceptionResponse;
import ru.kirill.restapi.security.JwtTokenFilter;

import static org.springframework.security.config.Customizer.withDefaults;
import static ru.kirill.restapi.util.ResponseUtil.createExceptionResponse;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity
@EnableConfigurationProperties(JwtProperties.class)
@SecurityScheme(type = SecuritySchemeType.HTTP, name = "basic", scheme = "basic")
public class SecurityConfig {

    private final ObjectMapper objectMapper;

    @Bean
    @SneakyThrows
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           AuthenticationEntryPoint authenticationEntryPoint,
                                           AccessDeniedHandler accessDeniedHandler,
                                           JwtTokenFilter jwtTokenFilter) {
        return http
                .csrf(CsrfConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(withDefaults())
                .authorizeHttpRequests(config -> config
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/webjars/**",
                                "/api/auth/**",
                                "/error",
                                "/api/statistics/**"
                        ).permitAll()
                        .requestMatchers(HttpMethod.POST, "/member").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults())
                .exceptionHandling(config -> config
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler)
                )
                .addFilterAfter(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
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

    @Bean
    @SneakyThrows
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) {
        return authConfig.getAuthenticationManager();
    }
}
