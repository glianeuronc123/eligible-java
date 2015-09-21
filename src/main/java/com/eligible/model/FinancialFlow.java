package com.eligible.model;

import java.util.List;

/**
 * Created by ankitdimania on 18/09/15.
 */
public class FinancialFlow extends EligibleObject {
    String percent;
    String amount;
    String timePeriod;
    String timePeriodLabel;
    String level;
    String network;
    String insuranceType;
    String insuranceTypeLabel;
    String pos;
    String posLabel;
    Boolean authorizationRequired;
    String description;
    String quantityCode;
    String quantityLabel;
    String quantity;
    List<ContactDetail> contactDetails;
    Dates dates;
    List<String> comments;


    public String getPercent() {
        return percent;
    }

    public String getAmount() {
        return amount;
    }

    public String getTimePeriod() {
        return timePeriod;
    }

    public String getTimePeriodLabel() {
        return timePeriodLabel;
    }

    public String getLevel() {
        return level;
    }

    public String getNetwork() {
        return network;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public String getInsuranceTypeLabel() {
        return insuranceTypeLabel;
    }

    public String getPos() {
        return pos;
    }

    public String getPosLabel() {
        return posLabel;
    }

    public Boolean getAuthorizationRequired() {
        return authorizationRequired;
    }

    public String getDescription() {
        return description;
    }

    public String getQuantityCode() {
        return quantityCode;
    }

    public String getQuantityLabel() {
        return quantityLabel;
    }

    public String getQuantity() {
        return quantity;
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
}
