package ru.kirill.restapi.exception;

public class AdminDeleteException extends ConflictException {

    public AdminDeleteException() {
        super("Admin is unmodifiable");
    }
}
