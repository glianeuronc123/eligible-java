package com.eligible.model.enrollmentnpi;

import com.eligible.model.EligibleObject;
import com.eligible.model.EnrollmentNpi;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = false)
public class EnrollmentNpiQueryResponse extends EligibleObject {

    List<EnrollmentNpiResponse> enrollmentNpis;
    Integer page;
    Integer perPage;
    Integer numOfPages;
    Integer total;

}
