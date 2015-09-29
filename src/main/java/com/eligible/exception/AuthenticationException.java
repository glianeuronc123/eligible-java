package com.eligible.exception;

/**
 * Exception while authenticating.
 */
public class AuthenticationException extends EligibleException {

    /**
     * Create Authentication Exception.
     * @param message
     */
    public AuthenticationException(String message) {
        super(message);
    }

    private static final long serialVersionUID = 1L;

}
