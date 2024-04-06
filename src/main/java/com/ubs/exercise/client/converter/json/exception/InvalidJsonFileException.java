package com.ubs.exercise.client.converter.json.exception;

public class InvalidJsonFileException extends RuntimeException{
    public InvalidJsonFileException() {
    }

    public InvalidJsonFileException(String message) {
        super(message);
    }

    public InvalidJsonFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidJsonFileException(Throwable cause) {
        super(cause);
    }

    public InvalidJsonFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
