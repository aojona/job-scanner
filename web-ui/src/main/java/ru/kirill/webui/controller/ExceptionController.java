package ru.kirill.webui.controller;

import feign.FeignException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kirill.webui.mapper.FeignExceptionMapper;

@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionController {

    private static final String ERROR_ATTRIBUTE = "error";

    private final FeignExceptionMapper exceptionMapper;

    @ExceptionHandler
    public String handle(FeignException e, HttpServletRequest request, RedirectAttributes attributes) {
        if (e.contentUTF8().isBlank()) {
            String message = HttpStatus.valueOf(e.status()).getReasonPhrase();
            attributes.addAttribute(ERROR_ATTRIBUTE, message);
        } else {
            String message = exceptionMapper.convert(e).getError();
            attributes.addAttribute(ERROR_ATTRIBUTE, message);
        }
        return "redirect:" + request.getRequestURI();
    }
}
