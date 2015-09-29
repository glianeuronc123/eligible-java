package com.eligible.util;

import com.eligible.net.RequestOptions;
import org.junit.Test;

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

    @Test(expected = RequestOptions.InvalidRequestOptionsException.class)
    public void testNormalizeStringEmpty() {
        normalizeString("");
    }

    @Test(expected = RequestOptions.InvalidRequestOptionsException.class)
    public void testNormalizeStringSpaces() {
        normalizeString("  ");
    }

}
