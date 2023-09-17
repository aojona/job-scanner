package ru.kirill.restapi.exception;

public class MemberAlreadyExistException extends ConflictException {
    public MemberAlreadyExistException() {
        super("This username is already taken");
    }
}
