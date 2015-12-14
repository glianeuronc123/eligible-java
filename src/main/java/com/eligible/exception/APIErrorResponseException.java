package com.eligible.exception;

import com.eligible.model.APIErrorResponse;
import lombok.Getter;

/**
 * Internal API exception.
 */
public class APIErrorResponseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    @Getter
    private final APIErrorResponse apiResponse;

    /**
     * Create APIErrorResponseException with message, requestId and cause.
     *
     * @param message message
     * @param apiResponse EligibleAPI response
     */
    public APIErrorResponseException(String message, APIErrorResponse apiResponse) {
        super(message);
        this.apiResponse = apiResponse;
    }

    /** {@inheritDoc} */
    public String toString() {
        String reqIdStr = "";
        if (apiResponse != null) {
            reqIdStr = "; apiResponse: " + apiResponse;
        }
        return super.toString() + reqIdStr;
    }
}
