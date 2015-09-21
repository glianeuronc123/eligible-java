package com.eligible.model;

/**
 */
public class Reserve extends EligibleObject {
    Double monetaryAmount;
    Integer totalDays;
    Integer remainingDays;


    public Double getMonetaryAmount() {
        return monetaryAmount;
    }

    public Integer getTotalDays() {
        return totalDays;
    }

    public Integer getRemainingDays() {
        return remainingDays;
    }
}
