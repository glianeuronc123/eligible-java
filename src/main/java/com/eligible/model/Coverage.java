package com.eligible.model;

import com.eligible.exception.APIConnectionException;
import com.eligible.exception.APIException;
import com.eligible.exception.AuthenticationException;
import com.eligible.exception.InvalidRequestException;
import com.eligible.net.APIResource;
import com.eligible.net.RequestMethod;
import com.eligible.net.RequestOptions;

import java.util.List;
import java.util.Map;

public class Coverage extends APIResource {
    String createdAt;
    String eligibleId;
    KnownIssues knownIssues;
    String type;
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


    public String getEligibleId() {
        return eligibleId;
    }

    public String getId() {
        return getEligibleId();
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public KnownIssues getKnownIssues() {
        return knownIssues;
    }

    public String getType() {
        return type;
    }

    public Demographics getDemographics() {
        return demographics;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public Plan getPlan() {
        return plan;
    }

    public List<Service> getServices() {
        return services;
    }

    public static class Medicare extends APIResource {
        String createdAt;
        String eligibleId;
        KnownIssues knownIssues;
        String type;
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
        Map<String, Map> planDetails;
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


        public String getEligibleId() {
            return eligibleId;
        }

        public String getId() {
            return getEligibleId();
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public KnownIssues getKnownIssues() {
            return knownIssues;
        }

        public String getType() {
            return type;
        }

        public String getDateOfDeath() {
            return dateOfDeath;
        }

        public String getLastName() {
            return lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getMiddleName() {
            return middleName;
        }

        public String getMemberId() {
            return memberId;
        }

        public String getGroupId() {
            return groupId;
        }

        public String getGroupName() {
            return groupName;
        }

        public String getDob() {
            return dob;
        }

        public String getGender() {
            return gender;
        }

        public String getRelationship() {
            return relationship;
        }

        public String getRelationshipCode() {
            return relationshipCode;
        }

        public Address getAddress() {
            return address;
        }

        public String getPayerName() {
            return payerName;
        }

        public String getPayerId() {
            return payerId;
        }

        public String getPlanNumber() {
            return planNumber;
        }

        public DateRange getEligibiltyDates() {
            return eligibiltyDates;
        }

        public DateRange getInactivityDates() {
            return inactivityDates;
        }

        public Map<String, String> getPlanTypes() {
            return planTypes;
        }

        public Map<String, Map> getPlanDetails() {
            return planDetails;
        }

        public List<RequestedServiceType> getRequestedServiceTypes() {
            return requestedServiceTypes;
        }

        public List<RequestedProcedureCode> getRequestedProcedureCodes() {
            return requestedProcedureCodes;
        }

        public HistoricDetails getHistory() {
            return history;
        }
    }
}
