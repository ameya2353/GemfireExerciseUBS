package com.ubs.exercise.converter.exception;

public class CsvConverterException extends RuntimeException {

    public CsvConverterException() {
    }

    public CsvConverterException(String message) {
        super(message);
    }

    public CsvConverterException(String message, Throwable cause) {
        super(message, cause);
    }

    public CsvConverterException(Throwable cause) {
        super(cause);
    }

    public CsvConverterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
