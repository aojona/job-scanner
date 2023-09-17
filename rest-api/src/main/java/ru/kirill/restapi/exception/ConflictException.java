package ru.kirill.restapi.exception;

import lombok.Getter;

@Getter
public class ConflictException extends RuntimeException {

    public ConflictException(String message) {
        super(message);
    }
}
