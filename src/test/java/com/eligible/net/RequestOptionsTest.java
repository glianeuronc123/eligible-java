package com.eligible.net;

import com.eligible.Eligible;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RequestOptionsTest {
    private static final String API_KEY = "test_api_key";
    private static final String API_VERSION = "test_api_version";
    private static final boolean TEST = true;

    @Test
    public void testGetDefault() {
        RequestOptions ro = RequestOptions.getDefault();
        verifyRequestOptions(ro, Eligible.apiKey, Eligible.apiVersion, Eligible.isTest);
    }

    @Test
    public void testDefaultBuilder() {
        RequestOptions ro = RequestOptions.builder().build();
        verifyRequestOptions(ro, Eligible.apiKey, Eligible.apiVersion, Eligible.isTest);
    }

    @Test
    public void testRequestOptionsBuilder() {
        RequestOptions ro = RequestOptions.getDefault().toBuilder()
                                .setApiKey(API_KEY)
                                .setApiVersion(API_VERSION)
                                .setTest(TEST).build();
        verifyRequestOptions(ro, API_KEY, API_VERSION, TEST);
    }

    private void verifyRequestOptions(RequestOptions ro, String apiKey, String apiVersion, boolean test) {
        assertEquals(apiKey, ro.getApiKey());
        assertEquals(apiVersion, ro.getApiVersion());
        assertEquals(test, ro.isTest());
    }
}
