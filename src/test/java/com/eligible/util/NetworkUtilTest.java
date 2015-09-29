package com.eligible.util;

import org.junit.Assert;
import org.junit.Test;

import static com.eligible.util.StringUtil.normalizeString;

public class NetworkUtilTest {

    @Test
    public void testUrlEncode() {
        Assert.assertEquals("abc", normalizeString("abc"));
    }

    @Test
    public void testUrlEncodeNull() {
        Assert.assertEquals(null, normalizeString(null));
    }
}
