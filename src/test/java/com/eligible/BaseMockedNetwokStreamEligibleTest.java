package com.eligible;

import org.junit.After;
import org.junit.Before;

import javax.net.ssl.HttpsURLConnection;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

import static com.eligible.net.LiveEligibleResponseGetter.CUSTOM_URL_STREAM_HANDLER_PROPERTY_NAME;
import static com.eligible.util.NetworkUtil.CHARSET;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/** Eligible Test base for mocking {@link java.net.HttpURLConnection}. */
public class BaseMockedNetwokStreamEligibleTest extends BaseEligibleTest {

    public static HttpsURLConnection urlConnectionMock;

    /** Setup mock for HTTPURLConnection. */
    @Before
    public void setUpMock() {
        urlConnectionMock = mock(HttpsURLConnection.class);
        System.setProperty(CUSTOM_URL_STREAM_HANDLER_PROPERTY_NAME, MockURLStreamHandler.class.getName());
    }

    /** This needs to be done because tests aren't isolated in Java. */
    @After
    public void unmockEligibleResponseGetter() {
        System.clearProperty(CUSTOM_URL_STREAM_HANDLER_PROPERTY_NAME);
    }

    protected static <T> void stubNetworkStream(String response) throws Exception {
        when(urlConnectionMock.getResponseCode()).thenReturn(HTTP_OK);
        when(urlConnectionMock.getInputStream()).thenReturn(new ByteArrayInputStream(response.getBytes(CHARSET)));
    }


    /**
     * Test {@link URLStreamHandler} for mocking {@link URLConnection}.
     */
    public static class MockURLStreamHandler extends URLStreamHandler {

        @Override
        protected URLConnection openConnection(URL u) throws IOException {
            return urlConnectionMock;
        }
    }
}
