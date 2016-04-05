package com.eligible.model.enrollmentnpi;

import com.eligible.model.EligibleObject;
import com.eligible.model.EnrollmentNpi;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class ReceivedPdfResponse extends EligibleObject {

    EnrollmentNpi.ReceivedPdf receivedPdf;

}
