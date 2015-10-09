package com.eligible.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class Error extends EligibleObject {
    String responseCode;
    String responseDescription;
    String agencyQualifierCode;
    String agencyQualifierDescription;
    String rejectReasonCode;
    String rejectReasonDescription;
    String followUpActionCode;
    String followUpActionDescription;
    String details;
}
