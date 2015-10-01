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

    public static Acknowledgements acknowledgements(String referenceId)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return acknowledgements(referenceId, null);
    }

    public static Acknowledgements queryAcknowledgements(Map<String, Object> params)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return queryAcknowledgements(params, null);
    }

    public static Claim all(Map<String, Object> params, RequestOptions options)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return request(RequestMethod.POST, classURL(Claim.class), params, Claim.class, options);
    }

    public static Acknowledgements acknowledgements(String referenceId, RequestOptions options)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return Acknowledgements.retrieve(referenceId, options);
    }

    public static Acknowledgements queryAcknowledgements(Map<String, Object> params, RequestOptions options)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return Acknowledgements.query(params, options);
    }


    public String getId() {
        return getReferenceId();
    }


    @Getter
    @EqualsAndHashCode(callSuper=false)
    public static class Acknowledgements extends APIResource {
        List<com.eligible.model.Acknowledgement> acknowledgements;
        String payerControlNumber;
        String referenceId;
        Integer page;
        Integer perPage;
        Integer numOfPages;
        Integer total;


        public static Acknowledgements retrieve(String referenceId)
                throws AuthenticationException, InvalidRequestException,
                APIConnectionException, APIException {
            return retrieve(referenceId, null);
        }

        public static Acknowledgements query(Map<String, Object> params)
                throws AuthenticationException, InvalidRequestException,
                APIConnectionException, APIException {
            return query(params, null);
        }

        public static Acknowledgements retrieve(String referenceId, RequestOptions options)
                throws AuthenticationException, InvalidRequestException,
                APIConnectionException, APIException {
            String url = String.format("%s/%s", instanceURL(Claim.class, referenceId), className(Acknowledgements.class));
            return request(RequestMethod.GET, url, null, Acknowledgements.class, options);
        }

        public static Acknowledgements query(Map<String, Object> params, RequestOptions options)
                throws AuthenticationException, InvalidRequestException,
                APIConnectionException, APIException {
            String url = String.format("%s/%s", classURL(Claim.class), className(Acknowledgements.class));
            return request(RequestMethod.GET, url, params, Acknowledgements.class, options);
        }
    }


    @Getter
    @EqualsAndHashCode(callSuper=false)
    public static class PaymentReports extends APIResource {


        public static Acknowledgements retrieve(String referenceId)
                throws AuthenticationException, InvalidRequestException,
                APIConnectionException, APIException {
            return retrieve(referenceId, null);
        }

        public static Acknowledgements query(Map<String, Object> params)
                throws AuthenticationException, InvalidRequestException,
                APIConnectionException, APIException {
            return query(params, null);
        }

        public static Acknowledgements retrieve(String referenceId, RequestOptions options)
                throws AuthenticationException, InvalidRequestException,
                APIConnectionException, APIException {
            String url = String.format("%s/%ss", instanceURL(Claim.class, referenceId), className(PaymentReports.class));
            return request(RequestMethod.GET, url, null, Acknowledgements.class, options);
        }

        public static Acknowledgements query(Map<String, Object> params, RequestOptions options)
                throws AuthenticationException, InvalidRequestException,
                APIConnectionException, APIException {
            String url = String.format("%s/%ss", classURL(Claim.class), className(PaymentReports.class));
            return request(RequestMethod.GET, url, params, Acknowledgements.class, options);
        }
    }

}
