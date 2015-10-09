package com.eligible.model.paymentstatus;

import com.eligible.model.EligibleObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = false)
public class Claim extends EligibleObject {
    String payerControlNumber;
    String traceNumber;
    String billType;
    String accountNumber;
    String prescriptionNumber;
    String voucherId;
    String startDate;
    String endDate;
    List<Status> statuses;
    List<ServiceInformation> serviceLines;
}
