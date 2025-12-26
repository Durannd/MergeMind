package com.ricael.mergemind.exceptions;

public class EmailInUseException extends RuntimeException {
    public EmailInUseException(String message) {
        super(message);
    }
}
