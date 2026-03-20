package com.ani.pm.exception;

public class InvalidClientTypeException extends RuntimeException {
    public InvalidClientTypeException(String type) {
        super("Unknown client type: " + type);
    }
}
