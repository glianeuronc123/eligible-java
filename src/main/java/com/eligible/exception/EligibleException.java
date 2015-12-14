package com.eligible.exception;

/**
 * Eligible base exception class.
 */
public abstract class EligibleException extends Exception {

    /**
     * Create Eligible Exception.
     *
     * @param message the detail message.
     */
    public EligibleException(String message) {
        super(message);
    }

    /**
     * Create Eligible Exception.
     *
     * @param message the detail message.
     * @param e root cause.
     */
    public EligibleException(String message, Throwable e) {
        super(message, e);
    }

    private static final long serialVersionUID = 1L;
}

