package com.eligible.model;

/**
 */
public class Deductible extends EligibleObject {
    String excluded;
    String remaining;
    String startDate;
    String endDate;


    public String getExcluded() {
        return excluded;
    }

    public String getRemaining() {
        return remaining;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
