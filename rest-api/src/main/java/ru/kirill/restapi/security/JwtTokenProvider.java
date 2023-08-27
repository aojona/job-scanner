package ru.kirill.restapi.security;

import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import ru.kirill.restapi.config.JwtProperties;
import ru.kirill.restapi.util.JwtUtil;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {

    private static final String AUTHORITIES_CLAIM = "authorities";

    private final JwtKey accessKey;

    public JwtTokenProvider(JwtProperties jwtProperties) {
        this.accessKey = jwtProperties.accessKey();
    }

    public String generateAccessToken(@NonNull SecurityUser securityUser) {
        Date expiration = JwtUtil.calculateExpiration(accessKey.getExpirationTime(), accessKey.getExpirationUnit());
        return Jwts
                .builder()
                .setId(securityUser.getId().toString())
                .setSubject(securityUser.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(expiration)
                .claim(AUTHORITIES_CLAIM, securityUser.getAuthorities())
                .signWith(accessKey.getSecret()).compact();
    }

    public boolean validateAccessToken(@NonNull String accessToken) {
        try {
            JwtUtil
                    .getJwtParser(accessKey.getSecret())
                    .parseClaimsJws(accessToken);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public JwtAuthentication getAuthentication(@NonNull String token) {
        return JwtUtil.buildAuthentication(token, accessKey.getSecret(), AUTHORITIES_CLAIM);
    }

    public ResponseCookie generateAccessTokenCookie(SecurityUser securityUser) {
        String token = generateAccessToken(securityUser);
        return JwtUtil.generateAccessTokenCookie(token, accessKey.getCookieName());
    }

    public String getAccessTokenFromCookies(HttpServletRequest request) {
        return JwtUtil.getTokenFromCookies(request, accessKey.getCookieName());
    }
}
