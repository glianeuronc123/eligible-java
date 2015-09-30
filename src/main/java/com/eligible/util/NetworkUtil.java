package com.eligible.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public abstract class NetworkUtil {

    public static final String CHARSET = "UTF-8";

    public static String urlEncode(String str) throws UnsupportedEncodingException {
        // Preserve original behavior that passing null for an object id will lead
        // to us actually making a request to /v1/foo/null
        if (str == null) {
            return null;
        } else {
            return URLEncoder.encode(str, CHARSET);
        }
    }
}
