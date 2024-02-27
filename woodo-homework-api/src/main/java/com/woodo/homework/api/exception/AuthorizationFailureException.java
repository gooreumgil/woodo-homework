package com.woodo.homework.api.exception;

public class AuthorizationFailureException extends RuntimeException {

    public AuthorizationFailureException(String message) {
        super(message);
    }
}
