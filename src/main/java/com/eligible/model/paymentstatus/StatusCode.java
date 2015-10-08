package com.eligible.model.paymentstatus;

import com.eligible.model.EligibleObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class StatusCode extends EligibleObject {
    String categoryCode;
    String categoryLabel;
    String statusCode;
    String statusLabel;
    String entityCode;
    String entityLabel;
}
