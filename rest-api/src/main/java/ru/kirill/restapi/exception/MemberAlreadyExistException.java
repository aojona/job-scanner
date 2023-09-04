package ru.kirill.restapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class MemberAlreadyExistException extends ResponseStatusException {
    public MemberAlreadyExistException() {
        super(HttpStatus.CONFLICT, "This username is already taken");
    }
}
