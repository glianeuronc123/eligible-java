package com.eligible.exception;

/**
 * Connection exception to API endpoint.
 */
public class APIConnectionException extends EligibleException {

    private static final long serialVersionUID = 1L;

    /**
     * Create APIConnectionException with message.
     *
     * @param message message
     */
    public APIConnectionException(String message) {
        super(message, null);
    }

    /**
     * Create APIConnectionException with message and cause.
     *
     * @param message message
     * @param e cause of this exception
     */
    public APIConnectionException(String message, Throwable e) {
        super(message, null, e);
    }

}
