package com.eligible.model;

import java.util.List;
import java.util.Map;

/**
 */
public class HistoricDetails extends EligibleObject {
    Map<String, Map> planDetails;
    List<RequestedServiceType> requestedServiceTypes;


    public Map<String, Map> getPlanDetails() {
        return planDetails;
    }

    public List<RequestedServiceType> getRequestedServiceTypes() {
        return requestedServiceTypes;
    }
}
