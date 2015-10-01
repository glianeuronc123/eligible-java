package com.eligible.net;

import com.eligible.exception.APIConnectionException;
import com.eligible.exception.APIException;
import com.eligible.exception.AuthenticationException;
import com.eligible.exception.InvalidRequestException;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Interface for making Eligible API calls.
 *
 * <p>It is primarily used for mocking API calls while testing.</p>
 */
public interface EligibleResponseGetter {

    /**
     * Make an Eligible API call to {@code url} and return the deserialized Java Model of {@link Type} {@code type}.
     *
     * @param method HTTP method of API call
     * @param url {@link java.net.URL} of API call
     * @param params request parameters
     * @param typeOfT {@link Type} of response for deserializing
     * @param type Type of HTTP request
     * @param options {@link RequestOptions} of making API call. {@code RequestOptions.getDefault()} will be used if
     *                                      {@code null} is provided.
     * @return API Response
     * @throws AuthenticationException
     * @throws InvalidRequestException
     * @throws APIConnectionException
     * @throws APIException
     */
    <T> T request(
            RequestMethod method,
            String url,
            Map<String, Object> params,
            Type typeOfT,
            RequestType type,
            RequestOptions options)
        throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException;
}

