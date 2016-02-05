package com.eligible.json.deserializer;

import com.eligible.net.APIResource;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * {@link JsonDeserializer} for deserializing {@link com.eligible.model.APIErrorResponse}.
 */
public class ErrorDeserializer implements JsonDeserializer<com.eligible.model.Error> {

    /** {@inheritDoc} */
    public com.eligible.model.Error deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        final Gson gson = APIResource.GSON;

        // Dates could be a list of Date.class, or a list of list of Date.class. Merging all list into one
        if (json.isJsonObject()) {
            JsonObject errorJson = json.getAsJsonObject();
            com.eligible.model.Error error = gson.fromJson(json, com.eligible.model.Error.class);

            JsonElement fuac = errorJson.get("follow-up_action_code");
            JsonElement fuad = errorJson.get("follow-up_action_description");

            if (fuac != null) {
                error.setFollowUpActionCode(fuac.getAsString());
                error.setFollowUpActionDescription(fuad.getAsString());
            }
            return error;
        } else {
            throw new IllegalArgumentException("Can not deserialize class Error. Expected an object but got: " + json);
        }
    }
}
