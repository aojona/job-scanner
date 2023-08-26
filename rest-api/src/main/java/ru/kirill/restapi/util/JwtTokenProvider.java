package ru.kirill.restapi.util;

import io.jsonwebtoken.*;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.kirill.restapi.security.JwtKey;
import ru.kirill.restapi.security.JwtProperties;
import ru.kirill.restapi.security.SecurityUser;
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
                .setSubject(securityUser.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(expiration)
                .claim(AUTHORITIES_CLAIM, securityUser.getAuthorities())
                .signWith(accessKey.getSecret()).compact();
    }
}
