package com.eligible.model;

import com.eligible.exception.APIConnectionException;
import com.eligible.exception.APIException;
import com.eligible.exception.AuthenticationException;
import com.eligible.exception.InvalidRequestException;
import com.eligible.net.APIResource;
import com.eligible.net.RequestMethod;
import com.eligible.net.RequestOptions;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;
import java.util.Map;


@Getter
@EqualsAndHashCode(callSuper=false)
public class Claim extends APIResource {
    String success;
    String createdAt;
    String referenceId;


    public static Claim all(Map<String, Object> params)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return all(params, null);
    }

    public static Claim all(Map<String, Object> params, RequestOptions options)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return request(RequestMethod.POST, classURL(Claim.class), params, Claim.class, options);
    }


    public String getId() {
        return getReferenceId();
    }

}
