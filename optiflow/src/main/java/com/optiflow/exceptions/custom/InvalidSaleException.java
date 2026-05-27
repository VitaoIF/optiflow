package com.optiflow.exceptions.custom;

public class InvalidSaleException extends RuntimeException {
    public InvalidSaleException(String message) {
        super(message);
    }
}
