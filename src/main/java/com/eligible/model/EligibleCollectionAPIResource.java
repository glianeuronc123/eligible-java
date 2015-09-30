package com.eligible.model;

import com.eligible.net.APIResource;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Delegate;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper=false)
public abstract class EligibleCollectionAPIResource<T> extends APIResource implements List<T> {
    @Delegate
    @Setter List<T> data;
}
