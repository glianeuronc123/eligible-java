package com.eligible.util;

import org.junit.Test;

import static com.eligible.util.NetworkUtil.getBoundary;
import static com.eligible.util.NetworkUtil.urlEncode;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class NetworkUtilTest {

    @Test
    public void testUrlEncode() throws Exception {
        assertEquals("abc", urlEncode("abc"));
    }

    @Test
    public void testUrlEncodeNull() throws Exception {
        assertNull(urlEncode(null));
    }

    @Test
    public void testGetBoundary() throws Exception {
        assertNotNull(getBoundary());
    }

    @Test
    public void testGetBoundaryParsing() throws Exception {
        assertNotNull(Long.parseLong(getBoundary()));
    }

}
