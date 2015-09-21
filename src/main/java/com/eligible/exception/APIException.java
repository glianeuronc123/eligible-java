package com.eligible.exception;

public class APIException extends EligibleException {

    private static final long serialVersionUID = 1L;

    public APIException(String message, String requestId, Throwable e) {
        super(message, requestId, e);
    }

}
