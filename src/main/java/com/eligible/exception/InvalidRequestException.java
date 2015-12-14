package com.eligible.exception;

import lombok.Getter;

/**
 * Invalid or Incomplete request exception.
 */
public class InvalidRequestException extends EligibleException {

    private static final long serialVersionUID = 1L;

    /**
     * Param name.
     */
    @Getter
    private final String param;


    /**
     * Create InvalidRequestException.
     *
     * @param message message
     */
    public InvalidRequestException(String message) {
        this(message, null);
    }

    /**
     * Create InvalidRequestException.
     *
     * @param message message
     * @param param param name
     */
    public InvalidRequestException(String message, String param) {
        super(message);
        this.param = param;
    }

    /**
     * Create InvalidRequestException.
     *
     * @param message message
     * @param param param name
     * @param e cause
     */
    public InvalidRequestException(String message, String param, Throwable e) {
        super(message, e);
        this.param = param;
    }
}
