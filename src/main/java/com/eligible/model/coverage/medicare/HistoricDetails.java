package com.eligible.model.coverage.medicare;

import com.eligible.model.EligibleObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@EqualsAndHashCode(callSuper = false)
public class HistoricDetails extends EligibleObject {
    Map<String, Map> planDetails;
    List<RequestedServiceType> requestedServiceTypes;
}
