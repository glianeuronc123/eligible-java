package com.eligible.model.claim;

import com.eligible.model.EligibleObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper=false)
public class Acknowledgement extends EligibleObject {
    String effectiveDate;
    String status;
    String message;
    List<com.eligible.model.claim.Error> errors;
}
