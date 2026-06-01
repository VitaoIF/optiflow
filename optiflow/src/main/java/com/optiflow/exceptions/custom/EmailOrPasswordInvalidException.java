package com.optiflow.exceptions.custom;

public class EmailOrPasswordInvalidException extends RuntimeException {
    public EmailOrPasswordInvalidException(String message) {
        super(message);
    }
}
