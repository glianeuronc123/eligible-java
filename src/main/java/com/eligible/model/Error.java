package com.eligible.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class Error extends EligibleObject {
    String responseCode;
    String responseDescription;
    String agencyQualifierCode;
    String agencyQualifierDescription;
    String rejectReasonCode;
    String rejectReasonDescription;
    @Setter
    String followUpActionCode;
    @Setter
    String followUpActionDescription;
    String details;
}
