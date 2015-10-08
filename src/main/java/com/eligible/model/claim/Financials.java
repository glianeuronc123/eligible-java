package com.eligible.model.claim;

import com.eligible.model.EligibleObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class Financials extends EligibleObject {
    String typeCode;
    String typeLabel;
    String totalPaymentAmount;
    Boolean credit;
    Boolean debit;
    String paymentMethodCode;
    String paymentMethodLabel;
    String paymentFormatCode;
    String paymentFormatLabel;
    String paymentDate;
    String paymentTraceNumber;
    DepositoryFinancialInstitution sender;
    DepositoryFinancialInstitution receiver;
}
