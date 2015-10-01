package com.eligible.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper=false)
public class Acknowledgement extends EligibleObject {
    String effectiveDate;
    String status;
    String message;
    List<Error> errors;
}
