package com.ubs.exercise.client.converter.xml.exception;

public class XmlConverterException extends RuntimeException {

    public XmlConverterException() {
    }

    public XmlConverterException(String message) {
        super(message);
    }

    public XmlConverterException(String message, Throwable cause) {
        super(message, cause);
    }

    public XmlConverterException(Throwable cause) {
        super(cause);
    }

    public XmlConverterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
