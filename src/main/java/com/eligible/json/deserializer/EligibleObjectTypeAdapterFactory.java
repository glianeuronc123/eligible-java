package com.eligible.json.deserializer;

import com.eligible.model.EligibleObject;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Map;

/**
 * {@link TypeAdapterFactory} for deserializing {@link EligibleObject}.
 */
public class EligibleObjectTypeAdapterFactory implements TypeAdapterFactory {

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
                EligibleObject eligibleObj = eligibleObjAdapter.fromJsonTree(object);
                eligibleObj.setRawValues(eligibleResMapAdapter.fromJsonTree(object));
                return eligibleObj;
            }
        }.nullSafe();

        return (TypeAdapter<T>) result;
    }
}
