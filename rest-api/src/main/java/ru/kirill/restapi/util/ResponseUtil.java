package ru.kirill.restapi.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import ru.kirill.commondto.response.ExceptionResponse;
import java.time.LocalDateTime;

@UtilityClass
public class ResponseUtil {

    public static ExceptionResponse createExceptionResponse(Exception e, HttpServletRequest request, int errorCode) {
        return ExceptionResponse
                .builder()
                .timestamp(LocalDateTime.now())
                .status(errorCode)
                .error(e.getMessage())
                .path(request.getRequestURI())
                .build();
    }
}
