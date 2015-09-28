package com.eligible.exception;

/**
 * Throttling exception.
 */
public class RateLimitException extends InvalidRequestException {

    /**
     * Create RateLimitException.
     *
     * @param message
     * @param param
     * @param requestId
     * @param e
     */
    public RateLimitException(String message, String param, String requestId, Throwable e) {
        super(message, param, requestId, e);
    }

}
