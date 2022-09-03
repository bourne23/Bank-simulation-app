package com.foxborn.exception;

public class BadRequestException extends RuntimeException {
    //pass a message
    public BadRequestException(String message) {
        super(message);
    }
}
