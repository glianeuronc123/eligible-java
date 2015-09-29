package com.eligible.exception;

/**
 * Eligible base exception class.
 */
public abstract class EligibleException extends Exception {

    /**
     * Create Eligible Exception.
     *
     * @param message
     */
    public EligibleException(String message) {
        super(message);
    }

    /**
     * Create Eligible Exception.
     *
     * @param message
     * @param e
     */
    public EligibleException(String message, Throwable e) {
        super(message, e);
    }

    private static final long serialVersionUID = 1L;
}

