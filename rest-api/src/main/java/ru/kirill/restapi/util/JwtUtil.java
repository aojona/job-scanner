package ru.kirill.restapi.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import org.flywaydb.core.internal.util.StringUtils;

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
}
