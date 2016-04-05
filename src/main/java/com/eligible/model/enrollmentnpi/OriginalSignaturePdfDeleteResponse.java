package com.eligible.model.enrollmentnpi;

import com.eligible.model.EligibleObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = false)
public class OriginalSignaturePdfDeleteResponse extends EligibleObject {
    String message;
}
