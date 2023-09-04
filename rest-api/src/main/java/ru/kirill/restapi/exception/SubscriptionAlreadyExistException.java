package ru.kirill.restapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SubscriptionAlreadyExistException extends ResponseStatusException {
    public SubscriptionAlreadyExistException() {
        super(HttpStatus.CONFLICT, "This subscription is already added");
    }
}
