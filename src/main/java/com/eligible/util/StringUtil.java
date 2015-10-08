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

    /**
     * Check if the string is empty or null.
     * @param param
     * @return true if empty.
     */
    public static boolean isEmpty(String param) {
        return param == null || param.isEmpty();
    }

    /**
     * Check if the string is null, empty or only spaces.
     * @param param
     * @return true if blank.
     */
    public static boolean isBlank(String param) {
        return param == null || param.trim().isEmpty();
    }
}
