package com.ricael.mergemind.exceptions;

public class NullPasswordException extends RuntimeException {
    public NullPasswordException(String message) {
        super(message);
    }
}
