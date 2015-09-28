package com.eligible.net;

import com.eligible.Eligible;
import com.eligible.exception.APIConnectionException;
import com.eligible.exception.APIException;
import com.eligible.exception.AuthenticationException;
import com.eligible.exception.InvalidRequestException;
import com.eligible.json.deserializer.DatesDeserializer;
import com.eligible.json.deserializer.EligibleObjectTypeAdapterFactory;
import com.eligible.json.deserializer.EligibleRawJsonObjectDeserializer;
import com.eligible.model.Dates;
import com.eligible.model.EligibleObject;
import com.eligible.model.EligibleRawJsonObject;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Setter;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.Map;

import static com.eligible.util.NetworkUtil.CHARSET;
import static com.eligible.util.NetworkUtil.urlEncode;

public abstract class APIResource extends EligibleObject {
    @Setter
    private static EligibleResponseGetter eligibleResponseGetter = new LiveEligibleResponseGetter();


    public static final Gson GSON = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapterFactory(new EligibleObjectTypeAdapterFactory())
            .registerTypeAdapter(Dates.class, new DatesDeserializer())
            .registerTypeAdapter(EligibleRawJsonObject.class, new EligibleRawJsonObjectDeserializer())
            .create();

    protected static String className(Class<?> clazz) {
        String className = clazz.getSimpleName().toLowerCase();

        if (className.equals("searchoptions")) {
            return "search_options";
        } else {
            return className;
        }
    }

    protected static String singleClassURL(Class<?> clazz) {
        return singleClassURL(clazz, Eligible.getApiBase());
    }

    protected static String singleClassURL(Class<?> clazz, String apiBase) {
        return String.format("%s/v1.5/%s", apiBase, className(clazz));
    }

    protected static String classURL(Class<?> clazz) {
        return classURL(clazz, Eligible.getApiBase());
    }

    protected static String classURL(Class<?> clazz, String apiBase) {
        return String.format("%ss", singleClassURL(clazz, apiBase));
    }

    protected static String instanceURL(Class<?> clazz, String id)
            throws InvalidRequestException {
        return instanceURL(clazz, id, Eligible.getApiBase());
    }

    protected static String instanceURL(Class<?> clazz, String id, String apiBase)
            throws InvalidRequestException {
        try {
            return String.format("%s/%s", classURL(clazz, apiBase), urlEncode(id));
        } catch (UnsupportedEncodingException e) {
            throw new InvalidRequestException("Unable to encode parameters to "
                    + CHARSET
                    + ". Please contact support@eligible.com for assistance.",
                    null, null, e);
        }
    }

    protected static <T> T multipartRequest(RequestMethod method,
                                            String url, Map<String, Object> params, Type typeOfT,
                                            RequestOptions options) throws AuthenticationException,
            InvalidRequestException, APIConnectionException,
            APIException {
        return APIResource.eligibleResponseGetter.request(method, url, params, typeOfT,
                RequestType.MULTIPART, options);
    }

    protected static <T> T request(RequestMethod method,
                                   String url, Map<String, Object> params, Type typeOfT,
                                   RequestOptions options) throws AuthenticationException,
            InvalidRequestException, APIConnectionException,
            APIException {
        return APIResource.eligibleResponseGetter.request(method, url, params, typeOfT,
                RequestType.NORMAL, options);
    }

    // override request method for type checking.
    protected static <T> T request(RequestMethod method,
                                   String url, Map<String, Object> params, Class<T> typeOfT,
                                   RequestOptions options) throws AuthenticationException,
            InvalidRequestException, APIConnectionException,
            APIException {
        return request(method, url, params, (Type) typeOfT, options);
    }
}
