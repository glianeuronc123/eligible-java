package com.eligible.model.claim;

import com.eligible.model.Address;
import com.eligible.model.EligibleObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = false)
public class Payee extends EligibleObject {
    String name;
    String npi;
    Address address;
    List<ReferenceInformation> additionalIds;
    List<ProviderAdjustment> adjustments;
    RemittanceDeliverMethod deliverMethod;
}
