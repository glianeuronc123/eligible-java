package com.eligible.json.deserializer;

import com.eligible.model.Date;
import com.eligible.model.Dates;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DatesDeserializer implements JsonDeserializer<Dates> {
    public static final Type DATE_LIST_TYPE = new TypeToken<List<Date>>() {}.getType();

    public Dates deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        // Dates could be a list of Date.class, or a list of list of Date.class. Merging all list into one
        if (json.isJsonArray()) {
            Dates collection = new Dates();
            collection.setData(new ArrayList<Date>());
            JsonArray jsonDates = json.getAsJsonArray();

            for(JsonElement jsonDate : jsonDates) {
                if(jsonDate.isJsonArray()) {
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
