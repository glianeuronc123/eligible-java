package com.eligible.util;

import java.security.InvalidParameterException;

/**
 * Utility methods for {@link String}.
 */
public abstract class StringUtil {

    /**
     * {@link String#trim()} the input.
     * @param param
     * @return trimmed String.
     * @throws InvalidParameterException if {@code param} is blank.
     */
    public static String normalizeString(String param) {
        // null values are considered "valid"
        if (param == null) {
            return null;
        }
        String normalized = param.trim();
        if (normalized.isEmpty()) {
            throw new InvalidParameterException("Empty parameter specified!");
        }
        return normalized;
    }
}
