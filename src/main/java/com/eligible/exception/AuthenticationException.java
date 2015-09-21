package com.eligible.exception;

public class AuthenticationException extends EligibleException {


    public AuthenticationException(String message, String requestId) {
        super(message, requestId);
    }

    private static final long serialVersionUID = 1L;

}
