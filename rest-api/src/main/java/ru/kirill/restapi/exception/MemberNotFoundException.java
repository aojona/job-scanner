package ru.kirill.restapi.exception;

public class MemberNotFoundException extends ConflictException {
    public MemberNotFoundException(Number id) {
        super("Member with id (" + id + ") is not found");
    }

    public MemberNotFoundException(String username) {
        super("Member with username (" + username + ") is not found");
    }
}
