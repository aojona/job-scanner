package ru.kirill.restapi.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.flywaydb.core.internal.util.StringUtils;
import ru.kirill.restapi.enums.Role;
import java.security.Key;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@UtilityClass
public class JwtUtil {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTHORIZATION_HEADER_PREFIX = "Bearer ";

    public String resolveTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(header) && header.startsWith(AUTHORIZATION_HEADER_PREFIX)) {
            return header.split("\\s+")[1];
        }
        return null;
    }

    public static Date calculateExpiration(long expirationTime, ChronoUnit expirationUnit) {
        ZonedDateTime expiration = ZonedDateTime.now().plus(expirationTime, expirationUnit);
        return Date.from(expiration.toInstant());
    }

    public static String extractUsername(@NonNull String token, @NonNull Key key) {
        return getTokenClaims(token, key).getSubject();
    }

    public static List<Role> extractAuthorities(@NonNull String token, @NonNull Key key, @NonNull String authClaim) {
        Claims claims = getTokenClaims(token, key);
        return Arrays
                .stream(claims
                        .get(authClaim, Object.class)
                        .toString()
                        .replaceAll("\\pP", "")
                        .split("\\s+")
                )
                .map(Role::valueOf)
                .toList();
    }

    public static Claims getTokenClaims(@NonNull String token, @NonNull Key key) {
        return getJwtParser(key)
                .parseClaimsJws(token)
                .getBody();
    }

    public static JwtParser getJwtParser(Key secret) {
        return Jwts.parserBuilder().setSigningKey(secret).build();
    }
}
