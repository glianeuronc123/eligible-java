package com.eligible.model;

import java.util.List;
import java.util.Map;

/**
 * Created by ankitdimania on 18/09/15.
 */
public class PreExistingCondition extends EligibleObject {
    List<Map<String, String>> waitingPeriod;


    public List<Map<String, String>> getWaitingPeriod() {
        return waitingPeriod;
    }
}
