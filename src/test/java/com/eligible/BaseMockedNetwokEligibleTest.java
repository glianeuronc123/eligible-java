package com.eligible;

import com.eligible.exception.EligibleException;
import com.eligible.net.*;
import com.eligible.util.ObjectUtils;
import lombok.AllArgsConstructor;
import org.junit.After;
import org.junit.Before;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;

import java.lang.reflect.Type;
import java.util.Map;

import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/** Eligible Test base for mocking {@link EligibleResponseGetter}. */
public class BaseMockedNetwokEligibleTest extends BaseEligibleTest {

    public static EligibleResponseGetter networkMock;

    /** Setup mock for EligibleResponseGetter. */
    @Before
    public void setUpMock() {
        networkMock = mock(EligibleResponseGetter.class);
        APIResource.setEligibleResponseGetter(networkMock);
    }

    /** This needs to be done because tests aren't isolated in Java. */
    @After
    public void unmockEligibleResponseGetter() {
        APIResource.setEligibleResponseGetter(new LiveEligibleResponseGetter());
    }

    protected static <T> void verifyGet(
            Type typeOfT,
            String url) throws EligibleException {
        verifyRequest(RequestMethod.GET, typeOfT, url, null,
                RequestType.NORMAL, RequestOptions.getDefault());
    }

    protected static <T> void verifyGet(
            Type typeOfT,
            String url,
            Map<String, Object> params) throws EligibleException {
        verifyRequest(RequestMethod.GET, typeOfT, url, params,
                RequestType.NORMAL, RequestOptions.getDefault());
    }

    protected static <T> void verifyGet(
            Type typeOfT,
            String url,
            RequestOptions requestOptions) throws EligibleException {
        verifyRequest(RequestMethod.GET, typeOfT, url, null,
                RequestType.NORMAL, requestOptions);
    }

    protected static <T> void verifyGet(
            Type typeOfT,
            String url,
            Map<String, Object> params,
            RequestOptions requestOptions) throws EligibleException {
        verifyRequest(RequestMethod.GET, typeOfT, url, params,
                RequestType.NORMAL, requestOptions);
    }

    protected static <T> void verifyPost(
            Type typeOfT,
            String url) throws EligibleException {
        verifyRequest(RequestMethod.POST, typeOfT, url, null,
                RequestType.NORMAL, RequestOptions.getDefault());
    }

    protected static <T> void verifyPost(
            Type typeOfT,
            String url,
            Map<String, Object> params) throws EligibleException {
        verifyRequest(RequestMethod.POST, typeOfT, url, params,
                RequestType.NORMAL, RequestOptions.getDefault());
    }

    protected static <T> void verifyPost(
            Type typeOfT,
            String url,
            RequestOptions requestOptions) throws EligibleException {
        verifyRequest(RequestMethod.POST, typeOfT, url, null,
                RequestType.NORMAL, requestOptions);
    }

    protected static <T> void verifyPost(
            Type typeOfT,
            String url,
            Map<String, Object> params,
            RequestOptions requestOptions) throws EligibleException {
        verifyRequest(RequestMethod.POST, typeOfT, url, params,
                RequestType.NORMAL, requestOptions);
    }

    protected static <T> void verifyRequest(
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

    protected static <T> void verifyRequest(
            RequestMethod method,
            String url,
            Map<String, Object> params,
            RequestType requestType,
            RequestOptions options) throws EligibleException {
        verify(networkMock).request(
                eq(method),
                eq(url),
                argThat(new ParamMapMatcher(params)),
                eq(requestType),
                argThat(new RequestOptionsMatcher(options)));
    }

    protected static <T> void stubNetwork(Type typeOfT, String response) throws EligibleException {
        when(networkMock.request(
                Mockito.any(RequestMethod.class),
                Mockito.anyString(),
                Mockito.<Map<String, Object>>any(),
                Mockito.<Class<T>>any(),
                Mockito.any(RequestType.class),
                Mockito.any(RequestOptions.class))).thenReturn(APIResource.GSON.fromJson(response, typeOfT));
    }

    protected static <T> void stubNetwork(EligibleResponse response) throws EligibleException {
        when(networkMock.request(
                Mockito.any(RequestMethod.class),
                Mockito.anyString(),
                Mockito.<Map<String, Object>>any(),
                Mockito.any(RequestType.class),
                Mockito.any(RequestOptions.class))).thenReturn(response);
    }

    @AllArgsConstructor
    private static class ParamMapMatcher extends ArgumentMatcher<Map<String, Object>> {
        private Map<String, Object> other;

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

    @AllArgsConstructor
    private static class RequestOptionsMatcher extends ArgumentMatcher<RequestOptions> {
        private RequestOptions other;

        /* Treat null reference as RequestOptions.getDefault() */
        public boolean matches(Object obj) {
            RequestOptions defaultOptions = RequestOptions.getDefault();
            if (obj == null) {
                return this.other == null || equals(this.other, defaultOptions);
            } else if (obj instanceof RequestOptions) {
                RequestOptions requestOptions = (RequestOptions) obj;
                if (this.other == null) {
                    return equals(requestOptions, defaultOptions);
                } else {
                    return equals(this.other, requestOptions);
                }
            } else {
                return false;
            }
        }

        private boolean equals(RequestOptions ro1, RequestOptions ro2) {
            if (!ObjectUtils.equals(ro1.getApiKey(), ro2.getApiKey())) {
                return false;
            }

            if (!ObjectUtils.equals(ro1.getApiVersion(), ro2.getApiVersion())) {
                return false;
            }

            if (!ObjectUtils.equals(ro1.isTest(), ro2.isTest())) {
                return false;
            }

            return true;
        }
    }

}
