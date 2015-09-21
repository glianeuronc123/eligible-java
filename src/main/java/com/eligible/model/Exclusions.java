package com.eligible.model;

import java.util.List;

/**
 * Created by ankitdimania on 18/09/15.
 */
public class Exclusions extends EligibleObject {
    List<NonCovered> noncovered;
    PreExistingCondition preExistingCondition;


    public List<NonCovered> getNonCovered() {
        return noncovered;
    }

    public PreExistingCondition getPreExistingCondition() {
        return preExistingCondition;
    }
}
