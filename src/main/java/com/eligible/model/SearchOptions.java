package com.eligible.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper=false)
public class SearchOptions extends EligibleObject {
    Boolean used;
    String parameters;
    Integer combinations;
}
