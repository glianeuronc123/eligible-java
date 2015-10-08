package com.eligible.model.claim;

import com.eligible.model.EligibleObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class DepositoryFinancialInstitution extends EligibleObject {
    String dfiIdQualifier;
    String dfiIdQualifierLabel;
    String dfiId;
    String accountType;
    String accountNumber;
    String id;
    String supplementalCode;
}
