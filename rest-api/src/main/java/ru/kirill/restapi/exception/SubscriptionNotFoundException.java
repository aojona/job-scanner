package ru.kirill.restapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SubscriptionNotFoundException extends ResponseStatusException {
    public SubscriptionNotFoundException(Number id) {
        super(HttpStatus.NOT_FOUND, "Subscription with id [" + id + "] is not found");
    }
}
