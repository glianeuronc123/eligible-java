package com.eligible.util;

/**
 * Utility methods for Object.
 */
public abstract class ObjectUtils {

    /**
     * Compares two objects for equality.
     * @param a first obj
     * @param b second obj
     * @return true if objects are equal
     */
    public static boolean equals(Object a, Object b) {
        return a == null ? b == null : a.equals(b);
    }
}
