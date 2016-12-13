package com.eligible.util;

import java.security.InvalidParameterException;

/**
 * Utility methods for {@link String}.
 */
public abstract class StringUtil {

    /**
     * {@link String#trim()} the input.
     *
     * @param param input
     * @return trimmed String
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
     * @param param input
     * @return true if empty.
     */
    public static boolean isEmpty(String param) {
        return param == null || param.isEmpty();
    }

    /**
     * Check if the string is not empty or null.
     * @param param input
     * @return true if not empty.
     */
    public static boolean isNotEmpty(String param) {
        return !isNotEmpty(param);
    }

    /**
     * Check if the string is null, empty or only spaces.
     * @param param input
     * @return true if blank.
     */
    public static boolean isBlank(String param) {
        return param == null || param.trim().isEmpty();
    }

    /**
     * Check if the string is not null, empty or only spaces.
     * @param param input
     * @return true if not blank.
     */
    public static boolean isNotBlank(String param) {
        return !isBlank(param);
    }
}
