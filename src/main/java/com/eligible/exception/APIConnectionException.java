package com.eligible.exception;

public class APIConnectionException extends EligibleException {

    private static final long serialVersionUID = 1L;

    public APIConnectionException(String message) {
        super(message, null);
    }

    public APIConnectionException(String message, Throwable e) {
        super(message, null, e);
    }

}
