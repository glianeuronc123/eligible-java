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
     * @param requestId requestId
     * @param e cause
     */
    public APIException(String message, String requestId, Throwable e) {
        super(message, requestId, e);
    }

}
