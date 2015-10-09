package com.eligible.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class APIErrorResponse extends EligibleObject {
    String createdAt;
    String eligibleId;
    Error error;
}
