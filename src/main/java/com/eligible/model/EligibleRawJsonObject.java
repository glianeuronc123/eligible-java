package com.eligible.model;

import com.google.gson.JsonObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Fallback class for when we do not recognize the object
 * that we have received.
 */
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class EligibleRawJsonObject extends EligibleObject {
    final JsonObject json;
}
