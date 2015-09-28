package com.eligible.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper=false)
public class FinancialFlowsList extends EligibleObject {
    List<FinancialFlow> amounts;
}
