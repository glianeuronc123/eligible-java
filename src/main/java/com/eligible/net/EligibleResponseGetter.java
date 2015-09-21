package com.eligible.net;

import com.eligible.exception.APIConnectionException;
import com.eligible.exception.APIException;
import com.eligible.exception.AuthenticationException;
import com.eligible.exception.InvalidRequestException;

import java.lang.reflect.Type;
import java.util.Map;

public interface EligibleResponseGetter {
    <T> T request(
            RequestMethod method,
            String url,
            Map<String, Object> params,
            Type typeOfT,
            RequestType type,
            RequestOptions options) throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException;
}

