package com.eligible.model;

import java.util.List;

/**
 * Created by ankitdimania on 18/09/15.
 */
public class AdditionalInsurancePolicy extends EligibleObject {
    String insuranceType;
    String insuranceTypeLabel;
    String coverageDescription;
    List<Reference> reference;
    String payerType;
    String payerTypeLabel;
    List<ContactDetail> contactDetails;
    Dates dates;
    List<String> comments;
    List<String> serviceTypeCodes;


    public String getInsuranceType() {
        return insuranceType;
    }

    public String getInsuranceTypeLabel() {
        return insuranceTypeLabel;
    }

    public String getCoverageDescription() {
        return coverageDescription;
    }

    public List<Reference> getReference() {
        return reference;
    }

    public String getPayerType() {
        return payerType;
    }

    public String getPayerTypeLabel() {
        return payerTypeLabel;
    }

    public List<ContactDetail> getContactDetails() {
        return contactDetails;
    }

    public Dates getDates() {
        return dates;
    }

    public List<String> getComments() {
        return comments;
    }

    public List<String> getServiceTypeCodes() {
        return serviceTypeCodes;
    }
}
