package com.eligible.model;

import com.google.gson.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public abstract class EligibleObject {

    Map<String, Object> rawValues = new HashMap<String, Object>();

    public static final ExclusionStrategy RAW_VALUES_EXCLUSION_STRATEGY = new ExclusionStrategy() {
        @Override
        public boolean shouldSkipField(FieldAttributes f) {
            return f.getName().equals("rawValues");
        }

        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            return false;
        }
    };

    public static final GsonBuilder GSON_BUILDER = new GsonBuilder().
            setPrettyPrinting().
            serializeNulls().
            setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);

    public static final Gson PRETTY_DUMP_GSON = GSON_BUILDER.create();

    public static final Gson PRETTY_PRINT_GSON = GSON_BUILDER.
            addSerializationExclusionStrategy(RAW_VALUES_EXCLUSION_STRATEGY).
            create();

    public Map<String, ?> getRawValues() {
        return rawValues;
    }

    public void setRawValues(Map<String, Object> rawValues) {
        this.rawValues = rawValues;
    }

    public <T> T get(String id) {
        return (T) (rawValues == null ? null : rawValues.get(id));
    }

    public String dump() {
        return String.format(
                "<%s@%s id=%s> JSON: %s",
                this.getClass().getName(),
                System.identityHashCode(this),
                this.getIdString(),
                PRETTY_DUMP_GSON.toJson(this));
    }

    @Override
    public String toString() {
        return String.format(
                "<%s@%s id=%s> JSON: %s",
                this.getClass().getName(),
                System.identityHashCode(this),
                this.getIdString(),
                PRETTY_PRINT_GSON.toJson(this));
    }

    private Object getIdString() {
        try {
            Method idField = this.getClass().getDeclaredMethod("getId");
            return idField.invoke(this);
        } catch (SecurityException e) {
            return "";
        } catch (IllegalArgumentException e) {
            return "";
        } catch (IllegalAccessException e) {
            return "";
        } catch (NoSuchMethodException e) {
            return "";
        } catch (InvocationTargetException e) {
            return "";
        }
    }

    protected static boolean equals(Object a, Object b) {
        return a == null ? b == null : a.equals(b);
    }
}
