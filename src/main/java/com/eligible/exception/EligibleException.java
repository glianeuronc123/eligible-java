package com.eligible.exception;

import lombok.Getter;

/**
 * Eligible base exception class.
 */
public abstract class EligibleException extends Exception {

    @Getter
    private final String requestId;

    /**
     * Create Eligible Exception.
     *
     * @param message
     * @param requestId
     */
    public EligibleException(String message, String requestId) {
        super(message);
        this.requestId = requestId;
    }

    /**
     * Create Eligible Exception.
     *
     * @param message
     * @param requestId
     * @param e
     */
    public EligibleException(String message, String requestId, Throwable e) {
        super(message, e);
        this.requestId = requestId;
    }

    private static final long serialVersionUID = 1L;

    /** {@inheritDoc} */
    public String toString() {
        String reqIdStr = "";
        if (requestId != null) {
            reqIdStr = "; request-id: " + requestId;
        }
        return super.toString() + reqIdStr;
    }
}

