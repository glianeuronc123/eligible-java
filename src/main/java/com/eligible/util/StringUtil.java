package com.eligible.util;

import com.eligible.net.RequestOptions;

public abstract class StringUtil {

    public static String normalizeString(String param) {
        // null values are considered "valid"
        if (param == null) {
            return null;
        }
        String normalized = param.trim();
        if (normalized.isEmpty()) {
            throw new RequestOptions.InvalidRequestOptionsException("Empty parameter specified!");
        }
        return normalized;
    }
}
