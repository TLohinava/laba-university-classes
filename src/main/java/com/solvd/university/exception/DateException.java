package com.solvd.university.exception;

public class DateException extends RuntimeException {

    public DateException(String message) {
        super(message);
    }

    public DateException(String message, Throwable cause) {
        super(message, cause);
    }

}
