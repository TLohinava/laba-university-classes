package com.solvd.university.exception;

public class DataInvalidException extends Exception {

    public DataInvalidException(String message) {
        super(message);
    }

    public DataInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

}
