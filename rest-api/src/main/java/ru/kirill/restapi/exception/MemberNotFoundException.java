package ru.kirill.restapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class MemberNotFoundException extends ResponseStatusException {
    public MemberNotFoundException(Number id) {
        super(HttpStatus.NOT_FOUND, "Member with id (" + id + ") is not found");
    }
}
