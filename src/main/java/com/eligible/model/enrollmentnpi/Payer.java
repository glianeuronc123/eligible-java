package com.eligible.model.enrollmentnpi;

import com.eligible.model.EligibleObject;
import com.eligible.model.claim.Contact;
import com.eligible.model.payer.Endpoint;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = false)
public class Payer extends EligibleObject {
    String id;
    List<String> endpoints;
    List<String> names;
}
