package com.eligible.model;

import com.google.gson.JsonObject;

/**
 * Fallback class for when we do not recognize the object
 * that we have received.
 */
public class EligibleRawJsonObject extends EligibleObject {
    JsonObject json;

    public EligibleRawJsonObject(JsonObject json) {
        this.json = json;
    }

    public JsonObject getJson() {
        return json;
    }

    public void setJson(JsonObject json) {
        this.json = json;
    }
}
