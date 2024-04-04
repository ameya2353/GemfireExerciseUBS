package com.ubs.exercise.converter.exception;

public class InvalidCsvFileException extends RuntimeException{
    public InvalidCsvFileException() {
    }

    public InvalidCsvFileException(String message) {
        super(message);
    }

    public InvalidCsvFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCsvFileException(Throwable cause) {
        super(cause);
    }

    public InvalidCsvFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
