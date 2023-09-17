package ru.kirill.restapi.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.kirill.commondto.response.ExceptionResponse;
import ru.kirill.restapi.exception.ConflictException;
import ru.kirill.restapi.util.ResponseUtil;

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
    public ResponseEntity<ExceptionResponse> handleException(SQLException e, HttpServletRequest httpServletRequest) {
        return handleException(e, httpServletRequest, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(ConflictException e, HttpServletRequest httpServletRequest) {
        return handleException(e, httpServletRequest, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                                                              HttpServletRequest httpServletRequest) {
        ExceptionResponse response = ResponseUtil.createExceptionResponse(e, httpServletRequest);
        return new ResponseEntity<>(response, e.getStatusCode());
    }
}
