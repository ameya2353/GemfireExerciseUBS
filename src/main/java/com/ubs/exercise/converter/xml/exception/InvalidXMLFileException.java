package com.ubs.exercise.converter.xml.exception;

public class InvalidXMLFileException extends RuntimeException{
    public InvalidXMLFileException() {
    }

    public InvalidXMLFileException(String message) {
        super(message);
    }

    public InvalidXMLFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidXMLFileException(Throwable cause) {
        super(cause);
    }

    public InvalidXMLFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
