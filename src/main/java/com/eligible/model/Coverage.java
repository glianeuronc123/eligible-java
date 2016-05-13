package com.eligible.model;

import com.eligible.exception.APIConnectionException;
import com.eligible.exception.APIException;
import com.eligible.exception.AuthenticationException;
import com.eligible.exception.InvalidRequestException;
import com.eligible.model.coverage.Demographics;
import com.eligible.model.coverage.Insurance;
import com.eligible.model.coverage.Plan;
import com.eligible.model.coverage.Service;
import com.eligible.model.coverage.costestimates.CostEstimate;
import com.eligible.model.coverage.costestimates.SearchOptions;
import com.eligible.model.coverage.medicare.DateRange;
import com.eligible.model.coverage.medicare.HistoricDetails;
import com.eligible.model.coverage.medicare.RequestedProcedureCode;
import com.eligible.model.coverage.medicare.RequestedServiceType;
import com.eligible.net.APIResource;
import com.eligible.net.RequestMethod;
import com.eligible.net.RequestOptions;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;
import java.util.Map;


@Getter
@EqualsAndHashCode(callSuper=false)
public class Coverage extends APIResource {
    String createdAt;
    String eligibleId;
    List<String> knownIssues;
    Demographics demographics;
    Insurance insurance;
    Plan plan;
    List<Service> services;


    public static Coverage all(Map<String, Object> params)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return all(params, null);
    }

    public static Medicare medicare(Map<String, Object> params)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return medicare(params, null);
    }

    public static CostEstimates costEstimate(Map<String, Object> params)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return costEstimate(params, null);
    }

    public static Coverage all(Map<String, Object> params, RequestOptions options)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        String url = String.format("%s/all", singleClassURL(Coverage.class));
        return request(RequestMethod.GET, url, params, Coverage.class, options);
    }

    public static Medicare medicare(Map<String, Object> params, RequestOptions options)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return Medicare.all(params, options);
    }

    public static CostEstimates costEstimate(Map<String, Object> params, RequestOptions options)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return CostEstimates.all(params, options);
    }


    public String getId() {
        return getEligibleId();
    }


    @Getter
    @EqualsAndHashCode(callSuper=false)
    public static class Medicare extends APIResource {
        String createdAt;
        String eligibleId;
        List<String> knownIssues;
        String dateOfDeath;
        String lastName;
        String firstName;
        String middleName;
        String memberId;
        String groupId;
        String groupName;
        String dob;
        String gender;
        String relationship;
        String relationshipCode;
        Address address;
        String payerName;
        String payerId;
        String planNumber;
        DateRange eligibiltyDates;
        DateRange inactivityDates;
        Map<String, String> planTypes;
        Map<String, Object> planDetails;
        List<RequestedServiceType> requestedServiceTypes;
        List<RequestedProcedureCode> requestedProcedureCodes;
        HistoricDetails history;

        public static Medicare all(Map<String, Object> params)
                throws AuthenticationException, InvalidRequestException,
                APIConnectionException, APIException {
            return all(params, null);
        }

        public static Medicare all(Map<String, Object> params, RequestOptions options)
                throws AuthenticationException, InvalidRequestException,
                APIConnectionException, APIException {
            String url = String.format("%s/%s", singleClassURL(Coverage.class), className(Medicare.class));
            return request(RequestMethod.GET, url, params, Medicare.class, options);
        }


        public String getId() {
            return getEligibleId();
        }
    }


    @Getter
    @EqualsAndHashCode(callSuper=false)
    public static class CostEstimates extends APIResource {
        String createdAt;
        String eligibleId;
        List<String> knownIssues;
        Demographics demographics;
        Insurance insurance;
        Plan plan;
        List<Service> services;
        SearchOptions searchOptions;
        List<CostEstimate> costEstimates;

        public static CostEstimates all(Map<String, Object> params)
                throws AuthenticationException, InvalidRequestException,
                APIConnectionException, APIException {
            return all(params, null);
        }

        public static CostEstimates all(Map<String, Object> params, RequestOptions options)
                throws AuthenticationException, InvalidRequestException,
                APIConnectionException, APIException {
            String url = String.format("%s/%s", singleClassURL(Coverage.class), className(CostEstimates.class));
            return request(RequestMethod.GET, url, params, CostEstimates.class, options);
        }


        public String getId() {
            return getEligibleId();
        }
    }
}
