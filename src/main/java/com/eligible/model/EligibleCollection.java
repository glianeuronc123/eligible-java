package com.eligible.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Delegate;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper=false)
public class EligibleCollection<T> extends EligibleObject implements List<T> {
    @Delegate
    @Setter
    List<T> data;
}
