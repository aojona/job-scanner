package ru.kirill.restapi.exception;

public class SubscriptionNotFoundException extends ConflictException {
    public SubscriptionNotFoundException(Number id) {
        super("Subscription with id (" + id + ") is not found");
    }

    public SubscriptionNotFoundException(String text) {
        super("Subscription with text (" + text + ") is not found");
    }
}
