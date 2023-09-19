package ru.kirill.webui.controller;

import feign.FeignException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kirill.webui.mapper.FeignExceptionMapper;
import ru.kirill.webui.util.CookieProvider;

@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionController {

    private static final String ERROR_ATTRIBUTE = "error";

    private static final String JWT_EXPIRED_ERROR_PATTERN = "JWT expired";
    private static final String JWT_EXPIRED_ERROR_MESSAGE = "Please, login to continue";
    private static final String LOGIN_ERROR_MESSAGE = "Bad credentials";

    private final FeignExceptionMapper exceptionMapper;
    private final CookieProvider cookieProvider;

    @ExceptionHandler
    public String handle(FeignException e, HttpServletRequest request, RedirectAttributes attributes, HttpServletResponse response) {
        if (e.contentUTF8().isBlank()) {
            String message = e.status() == 401
                    ? LOGIN_ERROR_MESSAGE
                    : HttpStatus.valueOf(e.status()).getReasonPhrase();
            attributes.addAttribute(ERROR_ATTRIBUTE, message);
        } else {
            String message = exceptionMapper.convert(e).getMessage();
            if (message.startsWith(JWT_EXPIRED_ERROR_PATTERN)) {
                cookieProvider.removeAccessTokenFromCookie(response);
                attributes.addAttribute(ERROR_ATTRIBUTE, JWT_EXPIRED_ERROR_MESSAGE);
                return "redirect:/auth/login";
            }
            attributes.addAttribute(ERROR_ATTRIBUTE, message);
        }
        return "redirect:" + request
                .getRequestURI()
                .replaceAll("(?<=member).*","")
                .replaceAll("(?<=admin).*","");
    }
}
