package ru.kirill.restapi.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.springframework.http.ResponseCookie;
import org.springframework.web.util.WebUtils;
import ru.kirill.restapi.enums.Role;
import ru.kirill.restapi.security.JwtAuthentication;
import ru.kirill.restapi.security.JwtPrincipal;
import java.security.Key;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@UtilityClass
public class JwtUtil {

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

    private static long extractId(@NonNull String token, @NonNull Key key) {
        return Long.parseLong(getTokenClaims(token, key).getId());
    }

    public static Claims getTokenClaims(@NonNull String token, @NonNull Key key) {
        return getJwtParser(key)
                .parseClaimsJws(token)
                .getBody();
    }

    public static JwtParser getJwtParser(Key secret) {
        return Jwts.parserBuilder().setSigningKey(secret).build();
    }

    public static String getTokenFromCookies(HttpServletRequest request, String cookieName) {
        Cookie cookie = WebUtils.getCookie(request, cookieName);
        if (cookie != null) {
            return cookie.getValue();
        } else {
            return null;
        }
    }

    public static ResponseCookie generateAccessTokenCookie(String token, String cookieName) {
        return ResponseCookie
                .from(cookieName, token)
                .httpOnly(true)
                .build();
    }

    public static JwtAuthentication buildAuthentication(@NonNull String token, @NonNull Key key, @NonNull String authClaim) {
        return JwtAuthentication
                .builder()
                .authenticated(true)
                .principal(new JwtPrincipal(
                        JwtUtil.extractId(token, key),
                        JwtUtil.extractUsername(token, key)
                ))
                .roles(JwtUtil.extractAuthorities(token, key, authClaim))
                .build();
    }
}
