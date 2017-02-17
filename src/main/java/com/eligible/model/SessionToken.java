package com.eligible.model;

import com.eligible.Eligible;
import com.eligible.exception.APIConnectionException;
import com.eligible.exception.APIException;
import com.eligible.exception.AuthenticationException;
import com.eligible.exception.InvalidRequestException;
import com.eligible.net.APIResource;
import com.eligible.net.RequestMethod;
import com.eligible.net.RequestOptions;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Map;

@Getter
@EqualsAndHashCode(callSuper = false)
public class SessionToken extends APIResource {
    String eligibleId;
    String endpoints;
    Long ttl;
    Long maxCalls;
    String createdAt;
    String expiresAt;
    String sessionToken;
    Boolean success;

    public static SessionToken get(Map<String, Object> params)
            throws APIException, AuthenticationException, InvalidRequestException, APIConnectionException {
        return get(params, null);
    }

    public static SessionToken get(Map<String, Object> params, RequestOptions options)
            throws APIException, AuthenticationException, InvalidRequestException, APIConnectionException {
        String url = String.format("%s/v1.5/session_tokens/create", Eligible.getApiBase());
        return request(RequestMethod.POST, url, params, SessionToken.class, options);
    }
}
