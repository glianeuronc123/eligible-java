package com.eligible.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Random;

/**
 * Utility methods for network.
 */
public abstract class NetworkUtil {

    /**
     * UTF-8 charset for encoding.
     */
    public static final String CHARSET = "UTF-8";

    /**
     * HTTP encode url content using {@link #CHARSET}.
     *
     * @param str input string for encoding
     * @return null if {@code is null}, else encoded string.
     * @throws UnsupportedEncodingException if {@link #CHARSET} is unsupported
     */
    public static String urlEncode(String str) throws UnsupportedEncodingException {
        // Preserve original behavior that passing null for an object id will lead
        // to us actually making a request to /v1/foo/null
        if (str == null) {
            return null;
        } else {
            return URLEncoder.encode(str, CHARSET);
        }
    }

    /**
     * Generate a random number for boundary in HTTP requests.
     *
     * @return boundary
     */
    public static String getBoundary() {
        Random random = new Random();
        long positiveRandomLong = Math.abs(random.nextLong());
        return String.valueOf(positiveRandomLong);
    }
}
