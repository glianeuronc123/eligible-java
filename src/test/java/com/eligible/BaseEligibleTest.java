package com.eligible;

import com.eligible.exception.EligibleException;
import com.eligible.net.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.Collections;
import java.util.Map;

import static org.mockito.Mockito.*;

public class BaseEligibleTest {

    protected static final Map<String, Object> DUMMAY_PARAMS = Collections.emptyMap();

    public static EligibleResponseGetter networkMock;
    public static HttpURLConnection urlConnectionMock;

    public static <T> void verifyGet(
            Type typeOfT,
            String url) throws EligibleException {
        verifyRequest(RequestMethod.GET, typeOfT, url, null,
                RequestType.NORMAL, RequestOptions.getDefault());
    }

    public static <T> void verifyGet(
            Type typeOfT,
            String url,
            Map<String, Object> params) throws EligibleException {
        verifyRequest(RequestMethod.GET, typeOfT, url, params,
                RequestType.NORMAL, RequestOptions.getDefault());
    }

    public static <T> void verifyGet(
            Type typeOfT,
            String url,
            RequestOptions requestOptions) throws EligibleException {
        verifyRequest(RequestMethod.GET, typeOfT, url, null,
                RequestType.NORMAL, requestOptions);
    }

    public static <T> void verifyGet(
            Type typeOfT,
            String url,
            Map<String, Object> params,
            RequestOptions requestOptions) throws EligibleException {
        verifyRequest(RequestMethod.GET, typeOfT, url, params,
                RequestType.NORMAL, requestOptions);
    }

    public static <T> void verifyPost(
            Type typeOfT,
            String url) throws EligibleException {
        verifyRequest(RequestMethod.POST, typeOfT, url, null,
                RequestType.NORMAL, RequestOptions.getDefault());
    }

    public static <T> void verifyPost(
            Type typeOfT,
            String url,
            Map<String, Object> params) throws EligibleException {
        verifyRequest(RequestMethod.POST, typeOfT, url, params,
                RequestType.NORMAL, RequestOptions.getDefault());
    }

    public static <T> void verifyPost(
            Type typeOfT,
            String url,
            RequestOptions requestOptions) throws EligibleException {
        verifyRequest(RequestMethod.POST, typeOfT, url, null,
                RequestType.NORMAL, requestOptions);
    }

    public static <T> void verifyPost(
            Type typeOfT,
            String url,
            Map<String, Object> params,
            RequestOptions requestOptions) throws EligibleException {
        verifyRequest(RequestMethod.POST, typeOfT, url, params,
                RequestType.NORMAL, requestOptions);
    }

    public static <T> void verifyRequest(
            RequestMethod method,
            Type typeOfT,
            String url,
            Map<String, Object> params,
            RequestType requestType,
            RequestOptions options) throws EligibleException {
        verify(networkMock).request(
                eq(method),
                eq(url),
                argThat(new ParamMapMatcher(params)),
                eq(typeOfT),
                eq(requestType),
                argThat(new RequestOptionsMatcher(options)));
    }

    public static <T> void stubNetwork(Type typeOfT, String response) throws EligibleException {
        when(networkMock.request(
                Mockito.any(RequestMethod.class),
                Mockito.anyString(),
                Mockito.<Map<String, Object>>any(),
                Mockito.<Class<T>>any(),
                Mockito.any(RequestType.class),
                Mockito.any(RequestOptions.class))).thenReturn(APIResource.GSON.fromJson(response, typeOfT));
    }

    public static <T> void stubNetworkStream(String response) throws Exception {
        when(urlConnectionMock.getResponseCode()).thenReturn(200);
        when(urlConnectionMock.getInputStream()).thenReturn(new StringBufferInputStream(response));
    }

    public static class ParamMapMatcher extends ArgumentMatcher<Map<String, Object>> {
        private Map<String, Object> other;

        public ParamMapMatcher(Map<String, Object> other) {
            this.other = other;
        }

        /* Treat null references as equal to empty maps */
        public boolean matches(Object obj) {
            if (obj == null) {
                return this.other == null || this.other.isEmpty();
            } else if (obj instanceof Map) {
                Map<String, Object> paramMap = (Map<String, Object>) obj;
                if (this.other == null) {
                    return paramMap.isEmpty();
                } else {
                    return this.other.equals(paramMap);
                }
            } else {
                return false;
            }
        }
    }

    public static class RequestOptionsMatcher extends ArgumentMatcher<RequestOptions> {
        private RequestOptions other;

        public RequestOptionsMatcher(RequestOptions other) {
            this.other = other;
        }

        /* Treat null reference as RequestOptions.getDefault() */
        public boolean matches(Object obj) {
            RequestOptions defaultOptions = RequestOptions.getDefault();
            if (obj == null) {
                return this.other == null || this.other.equals(defaultOptions);
            } else if (obj instanceof RequestOptions) {
                RequestOptions requestOptions = (RequestOptions) obj;
                if (this.other == null) {
                    return requestOptions.equals(defaultOptions);
                } else {
                    return this.other.equals(requestOptions);
                }
            } else {
                return false;
            }
        }
    }

    @BeforeClass
    public static void setUp() {
        Eligible.apiKey = "foobar";
        Eligible.isTest = true;
    }

    @Before
    public void setUpMock() {
        networkMock = mock(EligibleResponseGetter.class);
        urlConnectionMock = mock(HttpURLConnection.class);
    }

    protected String resource(String path) throws IOException {
        InputStream resource = getClass().getResourceAsStream(path);

        ByteArrayOutputStream os = new ByteArrayOutputStream(1024);
        byte[] buf = new byte[1024];

        for (int i = resource.read(buf); i > 0; i = resource.read(buf)) {
            os.write(buf, 0, i);
        }

        return os.toString("utf8");

    }


    public static class MockURLStreamHandler extends URLStreamHandler {

        @Override
        protected URLConnection openConnection(URL u) throws IOException {
            return urlConnectionMock;
        }
    }
}
