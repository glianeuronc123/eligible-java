package com.eligible.model;

/**
 * Created by ankitdimania on 18/09/15.
 */
public class Demographics extends EligibleObject {
    Demographic subscriber;
    Demographic dependent;


    public Demographic getSubscriber() {
        return subscriber;
    }

    public Demographic getDependent() {
        return dependent;
    }
}
