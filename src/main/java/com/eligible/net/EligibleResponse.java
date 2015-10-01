package com.eligible.net;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;

/**
 * Raw HTTP response fetched from Eligible API Calls.
 */
@Getter
@AllArgsConstructor
public class EligibleResponse {

    private final int responseCode;
    private final String responseBody;
    private final Map<String, List<String>> responseHeaders;

    /**
     * Create an {@link EligibleResponseGetter} with response code and body.
     *
     * @param responseCode HTTP response code
     * @param responseBody HTTP response body
     */
    public EligibleResponse(int responseCode, String responseBody) {
        this(responseCode, responseBody, null);
    }
}
