package com.eligible.exception;

/**
 * Exception while authenticating.
 */
public class AuthenticationException extends EligibleException {

    /**
     * Create Authentication Exception.
     * @param message
     * @param requestId
     */
    public AuthenticationException(String message, String requestId) {
        super(message, requestId);
    }

    private static final long serialVersionUID = 1L;

}
