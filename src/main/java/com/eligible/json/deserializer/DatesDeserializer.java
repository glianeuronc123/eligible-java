package com.eligible.json.deserializer;

import com.eligible.model.coverage.Date;
import com.eligible.model.coverage.Dates;
import com.eligible.net.APIResource;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link JsonDeserializer} for deserializing {@link Dates}.
 */
public class DatesDeserializer implements JsonDeserializer<Dates> {
    private static final Type DATE_LIST_TYPE = new TypeToken<List<Date>>() { }.getType();

    /** {@inheritDoc} */
    public Dates deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        final Gson gson = APIResource.GSON;

        // Dates could be a list of Date.class, or a list of list of Date.class. Merging all list into one
        if (json.isJsonArray()) {
            Dates collection = new Dates();
            collection.setData(new ArrayList<Date>());
            JsonArray jsonDates = json.getAsJsonArray();

            for (JsonElement jsonDate : jsonDates) {
                if (jsonDate.isJsonArray()) {
                    List<Date> dates = gson.fromJson(jsonDate, DATE_LIST_TYPE);
                    collection.addAll(dates);
                } else {
                    Date date = gson.fromJson(jsonDate, Date.class);
                    collection.add(date);
                }
            }
            return collection;
        } else {
            throw new IllegalArgumentException("Can not deserialize class Dates. Expected an array but got: " + json);
        }
    }
}
