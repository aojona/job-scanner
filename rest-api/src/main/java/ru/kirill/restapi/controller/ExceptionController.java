package ru.kirill.restapi.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.kirill.commondto.response.ExceptionResponse;
import java.sql.SQLException;

import static ru.kirill.restapi.util.ResponseUtil.createExceptionResponse;

@RestControllerAdvice
public class ExceptionController {

    public ResponseEntity<ExceptionResponse> handleException(Exception e, HttpServletRequest httpServletRequest,
                                                             HttpStatus status) {
        ExceptionResponse response = createExceptionResponse(e, httpServletRequest, status.value());
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(SQLException exception, HttpServletRequest httpServletRequest) {
        return handleException(exception, httpServletRequest, HttpStatus.BAD_REQUEST);
    }
}
