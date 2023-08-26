package ru.kirill.restapi.security;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import java.security.Key;
import java.time.temporal.ChronoUnit;

@Getter
public class JwtKey {

    private final Key secret;
    private final long expirationTime;
    private final ChronoUnit expirationUnit;

    public JwtKey(String secret, long expirationTime, ChronoUnit expirationUnit) {
        this.secret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        this.expirationTime = expirationTime;
        this.expirationUnit = expirationUnit;
    }
}
