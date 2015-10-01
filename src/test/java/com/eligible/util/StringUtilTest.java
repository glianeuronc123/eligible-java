package com.eligible.util;

import org.junit.Test;

import java.security.InvalidParameterException;

import static com.eligible.util.StringUtil.normalizeString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class StringUtilTest {

    @Test
    public void testNormalizeString() {
        assertEquals("test", normalizeString("test"));
    }

    @Test
    public void testNormalizeStringWithSpaces() {
        assertEquals("test", normalizeString("   test   "));
    }

    @Test
    public void testNormalizeStringNull() {
        assertNull(normalizeString(null));
    }

    @Test(expected = InvalidParameterException.class)
    public void testNormalizeStringEmpty() {
        normalizeString("");
    }

    @Test(expected = InvalidParameterException.class)
    public void testNormalizeStringSpaces() {
        normalizeString("  ");
    }

}
