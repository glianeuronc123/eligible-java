package com.eligible.json.deserializer;

import com.eligible.exception.APIErrorResponseException;
import com.eligible.model.APIErrorResponse;
import com.eligible.model.EligibleObject;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * {@link TypeAdapterFactory} for deserializing {@link EligibleObject}.
 */
public class EligibleObjectTypeAdapterFactory implements TypeAdapterFactory {

    private static final Gson GSON = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();

    private static final List<String> API_ERROR_RES_KEYS = Arrays.asList("created_at", "eligible_id", "error");
    private static final List<String> ERROR_RES_KEYS = Arrays.asList(
                                                        "response_code", "response_description",
                                                        "agency_qualifier_code", "agency_qualifier_description",
                                                        "reject_reason_code", "reject_reason_description",
                                                        "follow_up_action_code", "follow_up_action_description",
                                                        "details");

    /** {@inheritDoc} */
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        if (!EligibleObject.class.isAssignableFrom(type.getRawType())) {
            return null; // this class only deserializes 'EligibleObject' subtypes
        }

        final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
        final TypeAdapter<EligibleObject> eligibleObjAdapter =
                (TypeAdapter<EligibleObject>) gson.getDelegateAdapter(this, TypeToken.get(type.getRawType()));
        final TypeAdapter<Map> eligibleResMapAdapter = gson.getDelegateAdapter(this, TypeToken.get(Map.class));

        TypeAdapter<EligibleObject> result = new TypeAdapter<EligibleObject>() {
            public void write(JsonWriter out, EligibleObject value) throws IOException {
                eligibleObjAdapter.write(out, value);
            }

            public EligibleObject read(JsonReader in) throws IOException {
                JsonObject object = elementAdapter.read(in).getAsJsonObject();
                checkError(object);
                EligibleObject eligibleObj = eligibleObjAdapter.fromJsonTree(object);
                eligibleObj.setRawValues(eligibleResMapAdapter.fromJsonTree(object));
                return eligibleObj;
            }

            private void checkError(JsonObject object) throws APIErrorResponseException {
                if (checkContains(object, API_ERROR_RES_KEYS)) {
                    JsonElement errorObj = object.get("error");
                    if (checkContains(errorObj, ERROR_RES_KEYS)) {
                        APIErrorResponse response = GSON.fromJson(object, APIErrorResponse.class);
                        throw new APIErrorResponseException("API call replied with an error", response);
                    }
                }
            }

            private boolean checkContains(JsonElement object, List<String> memeberNames) {
                return object.isJsonObject() && checkContainsAll(object.getAsJsonObject(), memeberNames);
            }

            private boolean checkContainsAll(JsonObject object, List<String> memeberNames) {
                for (String memberName : memeberNames) {
                    if (!object.has(memberName)) {
                        return false;
                    }
                }
                return true;
            }
        }.nullSafe();

        return (TypeAdapter<T>) result;
    }
}
