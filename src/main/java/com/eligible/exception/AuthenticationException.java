package com.eligible.exception;

/**
 * Exception while authenticating.
 */
public class AuthenticationException extends EligibleException {

    /**
     * Create Authentication Exception.
     * @param message the detail message.
     */
    public AuthenticationException(String message) {
        super(message);
    }

    /**
     * Create Authentication Exception.
     * @param message the detail message.
     * @param e root cause.
     */
    public AuthenticationException(String message, Throwable e) {
        super(message, e);
    }

    private static final long serialVersionUID = 1L;

}
