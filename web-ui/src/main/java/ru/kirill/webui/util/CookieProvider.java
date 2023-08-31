package ru.kirill.webui.util;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.kirill.webui.config.CookieProperties;

@Component
@RequiredArgsConstructor
public class CookieProvider {

    private final CookieProperties cookieProperties;

    public void removeAccessTokenFromCookie(HttpServletResponse response) {
        response.addCookie(cookieProperties.removeAccessToken());
    }
}
