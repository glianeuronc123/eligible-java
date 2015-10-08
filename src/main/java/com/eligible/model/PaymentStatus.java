package com.eligible.model;


import com.eligible.exception.APIConnectionException;
import com.eligible.exception.APIException;
import com.eligible.exception.AuthenticationException;
import com.eligible.exception.InvalidRequestException;
import com.eligible.model.claim.Patient;
import com.eligible.model.claim.Provider;
import com.eligible.net.APIResource;
import com.eligible.net.RequestMethod;
import com.eligible.net.RequestOptions;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@EqualsAndHashCode(callSuper=false)
public class PaymentStatus extends APIResource {
    String createdAt;
    String eligibleId;
    List<String> knownIssues;
    com.eligible.model.paymentstatus.Payer payer;
    List<Provider> serviceProvider;
    List<Patient> patients;
    List<com.eligible.model.paymentstatus.Claim> claims;

    public static PaymentStatus retrieve(Map<String, Object> params)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return retrieve(params, null);
    }

    public static PaymentStatus retrieve(Map<String, Object> params, RequestOptions options)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return request(RequestMethod.GET, singleClassURL(PaymentStatus.class), params, PaymentStatus.class, options);
    }


    public String getId() {
        return getEligibleId();
    }
}
