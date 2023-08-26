package ru.kirill.restapi.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @SneakyThrows
    public void doFilterInternal(@NonNull HttpServletRequest request,
                                 @NonNull HttpServletResponse response,
                                 @NonNull FilterChain filterChain) {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context.getAuthentication() == null) {
            String token = jwtTokenProvider.getAccessTokenFromCookies(request);
            if (token != null && jwtTokenProvider.validateAccessToken(token)) {
                context.setAuthentication(jwtTokenProvider.getAuthentication(token));
            }
        }
        filterChain.doFilter(request, response);
    }
}
