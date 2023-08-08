package ru.kirill.restapi.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.kirill.commondto.response.ExceptionResponse;

import java.sql.SQLException;
import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    public ResponseEntity<ExceptionResponse> handleException(Exception e, HttpServletRequest httpServletRequest,
                                                             HttpStatus status) {
        log.error("Path: {}, status: {}, message: {}", httpServletRequest.getRequestURI(), status, e.getMessage());
        ExceptionResponse response = createResponse(e, httpServletRequest, status);
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(SQLException exception, HttpServletRequest httpServletRequest) {
        return handleException(exception, httpServletRequest, HttpStatus.BAD_REQUEST);
    }

    private static ExceptionResponse createResponse(Exception e, HttpServletRequest httpServletRequest, HttpStatus status) {
        return ExceptionResponse
                .builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(e.getMessage())
                .path(httpServletRequest.getRequestURI())
                .build();
    }
}
