package com.eligible.net;

import com.eligible.util.NetworkUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * Raw HTTP response fetched from Eligible API Calls.
 */
@Getter
@AllArgsConstructor
public class EligibleResponse {

    private final int responseCode;
    private final byte[] responseByteArray;
    private final String responseBody;
    private final Map<String, List<String>> responseHeaders;

    /**
     * Create an {@link EligibleResponseGetter} with response code and body.
     *
     * @param responseCode      HTTP response code
     * @param responseByteArray HTTP response body
     */
    public EligibleResponse(int responseCode, byte[] responseByteArray) throws UnsupportedEncodingException {
        this(responseCode, responseByteArray, new String(responseByteArray, NetworkUtil.CHARSET), null);
    }

    /**
     * Create an {@link EligibleResponseGetter} with response code and body.
     *
     * @param responseCode      HTTP response code
     * @param responseByteArray HTTP response body
     * @param responseHeaders   HTTP response headers
     */
    public EligibleResponse(int responseCode, byte[] responseByteArray, Map<String, List<String>> responseHeaders)
            throws UnsupportedEncodingException {
        this(responseCode, responseByteArray, new String(responseByteArray, NetworkUtil.CHARSET), responseHeaders);
    }
}
