package ru.kirill.restapi.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import ru.kirill.commondto.response.ExceptionResponse;

import java.time.ZonedDateTime;
import java.util.stream.Collectors;

@UtilityClass
public class ResponseUtil {

    public static ExceptionResponse createExceptionResponse(Exception e, HttpServletRequest request, int errorCode) {
        return ExceptionResponse
                .builder()
                .timestamp(ZonedDateTime.now())
                .status(errorCode)
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
    }

    public static ExceptionResponse createExceptionResponse(MethodArgumentNotValidException e, HttpServletRequest request) {
        return ExceptionResponse
                .builder()
                .timestamp(ZonedDateTime.now())
                .status(e.getStatusCode().value())
                .message(e
                        .getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.joining("; \n"))
                )
                .path(request.getRequestURI())
                .build();
    }
}
