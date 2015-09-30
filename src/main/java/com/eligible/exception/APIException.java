package com.eligible.exception;

/**
 * Internal API exception.
 */
public class APIException extends EligibleException {

    private static final long serialVersionUID = 1L;

    /**
     * Create APIConnectionException with message, requestId and cause.
     *
     * @param message message
     */
    public APIException(String message) {
        super(message);
    }

    /**
     * Create APIConnectionException with message, requestId and cause.
     *
     * @param message message
     * @param e cause
     */
    public APIException(String message, Throwable e) {
        super(message, e);
    }

}
