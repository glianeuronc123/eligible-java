package com.eligible.exception;

import lombok.Getter;

/**
 * Invalid or Incomplete request exception.
 */
public class InvalidRequestException extends EligibleException {

    private static final long serialVersionUID = 1L;

    /**
     * Get param name.
     *
     * @return param name
     */
    @Getter
    private final String param;

    /**
     * Create InvalidRequestException.
     *
     * @param message message
     * @param param param name
     * @param requestId request id
     * @param e cause
     */
    public InvalidRequestException(String message, String param, String requestId, Throwable e) {
        super(message, requestId, e);
        this.param = param;
    }
}
