package com.eligible.exception;

public abstract class EligibleException extends Exception {

    private final String requestId;

    public EligibleException(String message, String requestId) {
        super(message);
        this.requestId = requestId;
    }

    public EligibleException(String message, String requestId, Throwable e) {
        super(message, e);
        this.requestId = requestId;
    }

    private static final long serialVersionUID = 1L;

    public String getRequestId() {
        return requestId;
    }

    public String toString() {
        String reqIdStr = "";
        if (requestId != null) {
            reqIdStr = "; request-id: " + requestId;
        }
        return super.toString() + reqIdStr;
    }
}

