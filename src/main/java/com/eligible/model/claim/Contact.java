package com.eligible.model.claim;


import com.eligible.model.EligibleObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = false)
public class Contact extends EligibleObject {
    String departmentCode;
    String departmentLabel;
    String name;
    List<ReferenceInformation> details;
}
