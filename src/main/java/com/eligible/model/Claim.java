package com.eligible.model;

import com.eligible.exception.APIConnectionException;
import com.eligible.exception.APIException;
import com.eligible.exception.AuthenticationException;
import com.eligible.exception.InvalidRequestException;
import com.eligible.model.claim.Acknowledgement;
import com.eligible.model.claim.Financials;
import com.eligible.model.claim.Patient;
import com.eligible.model.claim.Payee;
import com.eligible.model.claim.Provider;
import com.eligible.net.APIResource;
import com.eligible.net.RequestMethod;
import com.eligible.net.RequestOptions;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;
import java.util.Map;

import static java.lang.String.format;
import static java.lang.String.valueOf;


@Getter
@EqualsAndHashCode(callSuper=false)
public class Claim extends APIResource {
    String success;
    String createdAt;
    String referenceId;


    public static Claim create(Map<String, Object> params)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return create(params, null);
    }

    public static Acknowledgements getAcknowledgements(String referenceId)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return getAcknowledgements(referenceId, null);
    }

    public static Acknowledgements queryAcknowledgements(Map<String, Object> params)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return queryAcknowledgements(params, null);
    }

    public static PaymentReport getPaymentReport(String referenceId)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return getPaymentReport(referenceId, (RequestOptions) null);
    }

    public static PaymentReport getPaymentReport(String referenceId, String paymentReportId)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return getPaymentReport(referenceId, paymentReportId, null);
    }

    public static PaymentReports queryPaymentReports(Map<String, Object> params)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return queryPaymentReports(params, null);
    }

    public static Claim create(Map<String, Object> params, RequestOptions options)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return request(RequestMethod.POST, classURL(Claim.class), params, Claim.class, options);
    }

    public static Acknowledgements getAcknowledgements(String referenceId, RequestOptions options)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return Acknowledgements.retrieve(referenceId, options);
    }

    public static Acknowledgements queryAcknowledgements(Map<String, Object> params, RequestOptions options)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return Acknowledgements.query(params, options);
    }

    public static PaymentReport getPaymentReport(String referenceId, RequestOptions options)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return PaymentReport.retrieve(referenceId, options);
    }

    public static PaymentReport getPaymentReport(String claimReferenceId, String paymentReportId, RequestOptions options)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return PaymentReport.retrieve(claimReferenceId, paymentReportId, options);
    }

    public static PaymentReports queryPaymentReports(Map<String, Object> params, RequestOptions options)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return PaymentReports.query(params, options);
    }


    public String getId() {
        return getReferenceId();
    }


    @Getter
    @EqualsAndHashCode(callSuper=false)
    public static class Acknowledgements extends APIResource {
        List<Acknowledgement> acknowledgements;
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
            String url = format("%s/%s", instanceURL(Claim.class, referenceId), className(Acknowledgements.class));
            return request(RequestMethod.GET, url, null, Acknowledgements.class, options);
        }

        public static Acknowledgements query(Map<String, Object> params, RequestOptions options)
                throws AuthenticationException, InvalidRequestException,
                APIConnectionException, APIException {
            String url = format("%s/%s", classURL(Claim.class), className(Acknowledgements.class));
            return request(RequestMethod.GET, url, params, Acknowledgements.class, options);
        }

        public String getId() {
            return getReferenceId();
        }
    }


    @Getter
    @EqualsAndHashCode(callSuper=false)
    public static class PaymentReport extends APIResource {
        String referenceId;
        String effectiveDate;
        com.eligible.model.claim.Payer payer;
        Financials financials;
        Payee payee;
        Patient patient;
        Patient correctedPatient;
        Patient otherPatient;
        Provider serviceProvider;
        com.eligible.model.claim.Claim claim;

        public static PaymentReport retrieve(String referenceId)
                throws AuthenticationException, InvalidRequestException,
                APIConnectionException, APIException {
            return retrieve(referenceId, (RequestOptions) null);
        }

        public static PaymentReport retrieve(String claimReferenceId, String paymentReportId)
                throws AuthenticationException, InvalidRequestException,
                APIConnectionException, APIException {
            return retrieve(claimReferenceId, paymentReportId, null);
        }

        public static PaymentReport retrieve(String referenceId, RequestOptions options)
                throws AuthenticationException, InvalidRequestException,
                APIConnectionException, APIException {
            String url = format("%s/%ss", instanceURL(Claim.class, referenceId), className(PaymentReport.class));
            return request(RequestMethod.GET, url, null, PaymentReport.class, options);
        }

        public static PaymentReport retrieve(String referenceId, String paymentReportId, RequestOptions options)
                throws AuthenticationException, InvalidRequestException,
                APIConnectionException, APIException {
            String url = format("%s/%ss/%s", instanceURL(Claim.class, referenceId), className(PaymentReport.class), paymentReportId);
            return request(RequestMethod.GET, url, null, PaymentReport.class, options);
        }


        public String getId() {
            return getReferenceId();
        }
    }


    @Getter
    @EqualsAndHashCode(callSuper=false)
    public static class PaymentReports extends APIResource {
        List<PaymentReport> reports;
        Integer page;
        Integer perPage;
        Integer numOfPages;
        Integer total;

        public static PaymentReports query(Map<String, Object> params)
                throws AuthenticationException, InvalidRequestException,
                APIConnectionException, APIException {
            return query(params, null);
        }

        public static PaymentReports query(Map<String, Object> params, RequestOptions options)
                throws AuthenticationException, InvalidRequestException,
                APIConnectionException, APIException {
            String url = format("%s/%s", classURL(Claim.class), className(PaymentReports.class));
            return request(RequestMethod.GET, url, params, PaymentReports.class, options);
        }


        public String getId() {
            return valueOf(getPage());
        }
    }

}
