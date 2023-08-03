package ru.kirill.ratelimiterservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BucketNotExistException extends ResponseStatusException {
    public BucketNotExistException(String message) {
        super(HttpStatus.BAD_REQUEST, "Bucket not exist: " + message);
    }
}
