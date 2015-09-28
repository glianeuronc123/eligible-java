package com.eligible.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@EqualsAndHashCode(callSuper=false)
public class HistoricDetails extends EligibleObject {
    Map<String, Map> planDetails;
    List<RequestedServiceType> requestedServiceTypes;
}
