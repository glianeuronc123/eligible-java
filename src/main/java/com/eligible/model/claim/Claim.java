package com.eligible.model.claim;

import com.eligible.model.EligibleObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = false)
public class Claim extends EligibleObject {
    String controlNumber;
    String receivedDate;
    String expirationDate;
    String filingIndicatorType;
    String filingIndicatorLabel;
    String placeOfService;
    String frequency;
    String responsibilitySequence;
    List<String> status;
    String drgCode;
    String drgQuantity;
    ClaimAmount amount;
    List<ClaimAdjustment> adjustments;
    ClaimQuantity quantity;
    List<ReferenceInformation> additionalIds;
    List<ReferenceInformation> renderingProviderIds;
    List<String> moaCodes;
    Double allowedAmount;
    List<Contact> contacts;
    List<ServiceInformation> serviceLines;
}
