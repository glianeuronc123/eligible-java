package com.eligible.net;

import com.eligible.Eligible;
import com.eligible.exception.APIConnectionException;
import com.eligible.exception.APIErrorResponseException;
import com.eligible.exception.APIException;
import com.eligible.exception.AuthenticationException;
import com.eligible.exception.InvalidRequestException;
import com.eligible.model.EligibleObject;
import com.eligible.util.StringUtil;
import com.google.gson.JsonElement;
import com.google.gson.stream.JsonReader;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.net.ssl.*;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLStreamHandler;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import static com.eligible.util.NetworkUtil.CHARSET;
import static com.eligible.util.NetworkUtil.getBoundary;
import static com.eligible.util.NetworkUtil.urlEncode;
import static com.eligible.util.StringUtil.isBlank;
import static com.eligible.util.StringUtil.isEmpty;
import static java.lang.String.format;
import static java.lang.String.valueOf;
import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_MULT_CHOICE;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static java.net.HttpURLConnection.HTTP_OK;
import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Default implementation of {@link EligibleResponseGetter} for making live Eligible API calls.
 */
public class LiveEligibleResponseGetter implements EligibleResponseGetter {

    /**
     * Set this property to override your environment's default URLStreamHandler;
     * Settings the property should not be needed in most environments.
     */
    public static final String CUSTOM_URL_STREAM_HANDLER_PROPERTY_NAME = "com.eligible.net.customURLStreamHandler";

    private static final String DNS_CACHE_TTL_PROPERTY_NAME = "networkaddress.cache.ttl";
    private static final int CONNECTION_TIMEOUT_MILLIS = (int) SECONDS.toMillis(30);
    private static final int READ_TIMEOUT_MILLIS = (int) SECONDS.toMillis(80);

    @Override
    public <T> T request(
            RequestMethod method,
            String url,
            Map<String, Object> params,
            Type typeOfT,
            RequestType type,
            RequestOptions options)
            throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
        EligibleResponse response = requestInternal(method, url, params, type, options);
        return APIResource.GSON.fromJson(response.getResponseBody(), typeOfT);
    }

    @Override
    public EligibleResponse request(
            RequestMethod method,
            String url,
            Map<String, Object> params,
            RequestType type,
            RequestOptions options)
            throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
        return requestInternal(method, url, params, type, options);
    }

    private static String urlEncodePair(String k, String v)
            throws UnsupportedEncodingException {
        return format("%s=%s", urlEncode(k), urlEncode(v));
    }

    static Map<String, String> getHeaders(RequestOptions options) {
        Map<String, String> headers = new HashMap<String, String>();
        String apiVersion = options.getApiVersion();
        headers.put("Accept-Charset", CHARSET);
        headers.put("Accept", "application/json");
        headers.put("User-Agent", format("eligible-java/%s", Eligible.VERSION));

        // debug headers
        String[] propertyNames = {"os.name", "os.version", "os.arch",
                "java.version", "java.vendor", "java.vm.version",
                "java.vm.vendor"};
        Map<String, String> propertyMap = new HashMap<String, String>();
        for (String propertyName : propertyNames) {
            propertyMap.put(propertyName, System.getProperty(propertyName));
        }
        propertyMap.put("bindings.version", Eligible.VERSION);
        propertyMap.put("lang", "Java");
        propertyMap.put("publisher", "Eligible");
        headers.put("X-Eligible-Client-User-Agent", APIResource.GSON.toJson(propertyMap));
        if (apiVersion != null) {
            headers.put("Eligible-Version", apiVersion);
        }
        return headers;
    }

    private static SSLSocketFactory getSocketFactory() throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException {
        // Perform customary SSL/TLS checks
        TrustManagerFactory tmf = TrustManagerFactory.getInstance("X509");
        tmf.init((KeyStore) null);
        TrustManager[] tm = tmf.getTrustManagers(); // Customary SSL/TLS checks

        List<TrustManager> tmList = new ArrayList<>(Arrays.asList(tm));
        tmList.add(new PubKeyManager());
        tm = tmList.toArray(tm);

        SSLContext context = SSLContext.getInstance("TLS");
        context.init(null, tm, null);
        return context.getSocketFactory();
    }

    private static HttpURLConnection createEligibleConnection(
            String url, RequestOptions options) throws IOException {
        URL eligibleURL;
        String customURLStreamHandlerClassName = System.getProperty(CUSTOM_URL_STREAM_HANDLER_PROPERTY_NAME, null);
        if (customURLStreamHandlerClassName != null) {
            // instantiate the custom handler provided
            try {
                Class<URLStreamHandler> clazz = (Class<URLStreamHandler>) Class
                        .forName(customURLStreamHandlerClassName);
                Constructor<URLStreamHandler> constructor = clazz.getConstructor();
                URLStreamHandler customHandler = constructor.newInstance();
                eligibleURL = new URL(null, url, customHandler);
            } catch (ReflectiveOperationException | SecurityException | IllegalArgumentException e) {
                throw new IOException(e);
            }
        } else {
            eligibleURL = new URL(url);
        }
        HttpsURLConnection conn;
        if (Eligible.getConnectionProxy() != null) {
            conn = (HttpsURLConnection) eligibleURL.openConnection(Eligible.getConnectionProxy());
            Authenticator.setDefault(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return Eligible.getProxyCredential();
                }
            });
        } else {
            conn = (HttpsURLConnection) eligibleURL.openConnection();
        }
        try {
            conn.setSSLSocketFactory(getSocketFactory());
        } catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
            throw new IOException(e);
        }
        conn.setConnectTimeout(CONNECTION_TIMEOUT_MILLIS);
        conn.setReadTimeout(READ_TIMEOUT_MILLIS);
        conn.setUseCaches(false);
        for (Map.Entry<String, String> header : getHeaders(options).entrySet()) {
            conn.setRequestProperty(header.getKey(), header.getValue());
        }

        return conn;
    }

    private static String formatURL(String url, String query) {
        if (isEmpty(query)) {
            return url;
        } else {
            // In some cases, URL can already contain a question mark (eg, upcoming invoice lines)
            String separator = url.contains("?") ? "&" : "?";
            return format("%s%s%s", url, separator, query);
        }
    }

    private static HttpURLConnection createGetConnection(
            String url, String query, RequestOptions options) throws IOException {
        String getURL = formatURL(url, query);
        HttpURLConnection conn = createEligibleConnection(getURL, options);
        conn.setRequestMethod("GET");

        return conn;
    }

    private static HttpURLConnection createPostConnection(
            String url, String query, RequestMethod requestMethod,
            RequestOptions options) throws IOException {
        HttpURLConnection conn = createEligibleConnection(url, options);

        conn.setDoOutput(true);
        conn.setRequestMethod(requestMethod.name());
        conn.setRequestProperty("Content-Type", format("application/json;charset=%s", CHARSET));

        try(OutputStream output = conn.getOutputStream()) {
            output.write(query.getBytes(CHARSET));
        }

        return conn;
    }

    private static HttpURLConnection createDeleteConnection(
            String url, String query, RequestOptions options) throws IOException {
        String deleteUrl = formatURL(url, query);
        HttpURLConnection conn = createEligibleConnection(deleteUrl, options);
        conn.setRequestMethod("DELETE");

        return conn;
    }

    static Map<String, Object> fillParams(Map<String, Object> params, RequestOptions options) {
        if (params == null) {
            params = new HashMap<String, Object>();
        }
        params.put("api_key", options.getApiKey());
        params.put("test", valueOf(options.isTest()));
        return params;
    }

    static String createHtmlQuery(Map<String, Object> params)
            throws UnsupportedEncodingException, InvalidRequestException {
        Map<String, String> flatParams = flattenParams(params);
        StringBuilder queryStringBuffer = new StringBuilder();

        for (Map.Entry<String, String> entry : flatParams.entrySet()) {
            if (queryStringBuffer.length() > 0) {
                queryStringBuffer.append("&");
            }
            queryStringBuffer.append(urlEncodePair(entry.getKey(),
                    entry.getValue()));
        }
        return queryStringBuffer.toString();
    }

    static String createJsonPayload(Map<String, Object> params)
            throws InvalidRequestException {
        return APIResource.GSON.toJson(params);
    }


    private static Map<String, String> flattenParams(Map<String, Object> params)
            throws InvalidRequestException {
        Map<String, String> flatParams = new LinkedHashMap<String, String>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Map<?, ?>) {
                Map<String, Object> flatNestedMap = new LinkedHashMap<String, Object>();
                Map<?, ?> nestedMap = (Map<?, ?>) value;
                for (Map.Entry<?, ?> nestedEntry : nestedMap.entrySet()) {
                    flatNestedMap.put(
                            format("%s[%s]", key, nestedEntry.getKey()),
                            nestedEntry.getValue());
                }
                flatParams.putAll(flattenParams(flatNestedMap));
            } else if (value instanceof List<?>) {
                Map<String, Object> flatNestedMap = new LinkedHashMap<String, Object>();
                Iterator<?> it = ((List<?>) value).iterator();
                for (int index = 0; it.hasNext(); ++index) {
                    flatNestedMap.put(format("%s[%s]", key, index), it.next());
                }
                flatParams.putAll(flattenParams(flatNestedMap));
            } else if ("".equals(value)) {
                throw new InvalidRequestException("You cannot set '" + key + "' to an empty string. "
                        + "We interpret empty strings as null in requests. "
                        + "You may set '" + key + "' to null to delete the property.",
                        key);
            } else if (value == null) {
                flatParams.put(key, "");
            } else {
                flatParams.put(key, value.toString());
            }
        }
        return flatParams;
    }

    @Getter
    @EqualsAndHashCode(callSuper = false)
    private static class Error extends EligibleObject {
        private String error;
    }

    private static byte[] getResponseBody(InputStream responseStream)
            throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        responseStream = new BufferedInputStream(responseStream);

        byte[] input = new byte[1024];
        int readCount = 0;
        while((readCount = responseStream.read(input)) != -1) {
            baos.write(input, 0, readCount);
        }

        responseStream.close();
        return baos.toByteArray();
    }

    private static EligibleResponse makeURLConnectionRequest(
            RequestMethod method, String url, Map<String, Object> params,
            RequestOptions options) throws APIConnectionException, InvalidRequestException {

        String query;
        try {
            query = createHtmlQuery(params);
        } catch (UnsupportedEncodingException e) {
            throw new InvalidRequestException("Unable to encode parameters to "
                    + CHARSET
                    + ". Please contact support@eligible.com for assistance.",
                    null, e);
        }

        HttpURLConnection conn = null;
        try {
            switch (method) {
                case GET:
                    conn = createGetConnection(url, query, options);
                    break;
                case POST:
                case PUT:
                    String payload = createJsonPayload(params);
                    conn = createPostConnection(url, payload, method, options);
                    break;
                case DELETE:
                    conn = createDeleteConnection(url, query, options);
                    break;
                default:
                    throw new APIConnectionException(
                            format("Unrecognized HTTP method %s. This indicates a bug in the Eligible bindings. "
                                    + "Please contact support@eligible.com for assistance.", method));
            }
            // trigger the request
            int rCode = conn.getResponseCode();
            byte[] rBody;
            Map<String, List<String>> headers;

            if (rCode >= HTTP_OK && rCode < HTTP_MULT_CHOICE) {
                rBody = getResponseBody(conn.getInputStream());
            } else {
                rBody = getResponseBody(conn.getErrorStream());
            }
            headers = conn.getHeaderFields();
            return new EligibleResponse(rCode, rBody, headers);

        } catch (IOException e) {
            throw new APIConnectionException(
                    format("IOException during API request to Eligible (%s): %s "
                                    + "Please check your internet connection and try again. If this problem persists,"
                                    + "you should check Eligible's service status at https://twitter.com/eligibleapi,"
                                    + " or let us know at support@eligible.com.",
                            Eligible.getApiBase(), e.getMessage()), e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    private static EligibleResponse requestInternal(RequestMethod method,
                                                    String url, Map<String, Object> params,
                                                    RequestType type, RequestOptions options)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        if (options == null) {
            options = RequestOptions.getDefault();
        }
        params = fillParams(params, options);

        String originalDNSCacheTTL = null;
        Boolean allowedToSetTTL = true;

        try {
            originalDNSCacheTTL = java.security.Security
                    .getProperty(DNS_CACHE_TTL_PROPERTY_NAME);
            // disable DNS cache
            java.security.Security
                    .setProperty(DNS_CACHE_TTL_PROPERTY_NAME, "0");
        } catch (SecurityException se) {
            allowedToSetTTL = false;
        }

        String apiKey = options.getApiKey();
        if (isBlank(apiKey)) {
            throw new AuthenticationException(
                    "No API key provided. (HINT: set your API key using 'Eligible.apiKey = <API-KEY>'. "
                            + "You can generate API keys from the Eligible web interface. "
                            + "See https://eligible.com/profile/access_keys for details "
                            + "or email support@eligible.com if you have questions.");
        }

        try {
            EligibleResponse response;
            switch (type) {
                case NORMAL:
                    response = getEligibleResponse(method, url, params, options);
                    break;
                case MULTIPART:
                    response = getMultipartEligibleResponse(method, url, params, options);
                    break;
                default:
                    throw new RuntimeException("Invalid APIResource request type. "
                            + "This indicates a bug in the Eligible bindings. Please contact "
                            + "support@eligible.com for assistance.");
            }
            int rCode = response.getResponseCode();
            String rBody = response.getResponseBody();

            if (rCode < HTTP_OK || rCode >= HTTP_MULT_CHOICE) {
                handleAPIError(rBody, rCode);
            }

            return response;

        } catch (APIErrorResponseException e) {
            String message = e.getApiResponse().getError().getDetails();
            throw new APIException(message, e);
        } finally {
            if (allowedToSetTTL) {
                if (originalDNSCacheTTL == null) {
                    // value unspecified by implementation
                    // DNS_CACHE_TTL_PROPERTY_NAME of -1 = cache forever
                    java.security.Security.setProperty(
                            DNS_CACHE_TTL_PROPERTY_NAME, "-1");
                } else {
                    java.security.Security.setProperty(
                            DNS_CACHE_TTL_PROPERTY_NAME, originalDNSCacheTTL);
                }
            }
        }
    }

    private static EligibleResponse getEligibleResponse(
            RequestMethod method, String url,
            Map<String, Object> params, RequestOptions options)
            throws InvalidRequestException, APIConnectionException,
            APIException {

        try {
            // HTTPSURLConnection verifies SSL cert by default
            return makeURLConnectionRequest(method, url, params, options);
        } catch (ClassCastException ce) {
            // appengine doesn't have HTTPSConnection, use URLFetch API
            String appEngineEnv = System.getProperty(
                    "com.google.appengine.runtime.environment", null);
            if (appEngineEnv != null) {
                return makeAppEngineRequest(method, url, params, options);
            } else {
                // non-appengine ClassCastException
                throw ce;
            }
        }
    }

    private static EligibleResponse getMultipartEligibleResponse(
            RequestMethod method, String url,
            Map<String, Object> params, RequestOptions options)
            throws InvalidRequestException, APIConnectionException,
            APIException {

        if (method != RequestMethod.POST && method != RequestMethod.PUT) {
            throw new InvalidRequestException(
                    "Multipart requests for HTTP methods other than POST/PUT "
                            + "are currently not supported.");
        }

        HttpURLConnection conn = null;
        try {
            conn = createEligibleConnection(url, options);

            String boundary = getBoundary();
            conn.setDoOutput(true);
            conn.setRequestMethod(method.name());
            conn.setRequestProperty("Content-Type", format("multipart/form-data; boundary=%s", boundary));

            MultipartProcessor multipartProcessor = null;
            try {
                multipartProcessor = new MultipartProcessor(
                        conn, boundary, CHARSET);

                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();

                    if (value instanceof File) {
                        File currentFile = (File) value;
                        if (!currentFile.exists()) {
                            throw new InvalidRequestException("File for key "
                                    + key + " must exist.");
                        } else if (!currentFile.isFile()) {
                            throw new InvalidRequestException("File for key "
                                    + key
                                    + " must be a file and not a directory.");
                        } else if (!currentFile.canRead()) {
                            throw new InvalidRequestException(
                                    "Must have read permissions on file for key "
                                            + key + ".");
                        }
                        multipartProcessor.addFileField(key, currentFile);
                    } else {
                        // We only allow a single level of nesting for params
                        // for multipart
                        multipartProcessor.addFormField(key, (String) value);
                    }
                }

            } finally {
                if (multipartProcessor != null) {
                    multipartProcessor.finish();
                }
            }

            // trigger the request
            int rCode = conn.getResponseCode();
            byte[] rBody;
            Map<String, List<String>> headers;

            if (rCode >= HTTP_OK && rCode < HTTP_MULT_CHOICE) {
                rBody = getResponseBody(conn.getInputStream());
            } else {
                rBody = getResponseBody(conn.getErrorStream());
            }
            headers = conn.getHeaderFields();
            return new EligibleResponse(rCode, rBody, headers);

        } catch (IOException e) {
            throw new APIConnectionException(
                    format("IOException during API request to Eligible (%s): %s "
                                    + "Please check your internet connection and try again. If this problem persists,"
                                    + "you should check Eligible's service status at https://twitter.com/eligibleapi,"
                                    + " or let us know at support@eligible.com.",
                            Eligible.getApiBase(), e.getMessage()), e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

    }

    private static void handleAPIError(String rBody, int rCode)
            throws InvalidRequestException, AuthenticationException,
            APIException {
        Exception rootCause = null;
        String message = null;

        JsonReader jsonReader = new JsonReader(new StringReader(rBody));
        jsonReader.setLenient(true);

        try {
            JsonElement rBodyJson = APIResource.GSON.fromJson(jsonReader, JsonElement.class);

            if (rBodyJson.isJsonObject()) {
                Error error = APIResource.GSON.fromJson(rBody, Error.class);
                message = error.getError();
            }

            if (StringUtil.isBlank(message)) {
                message = rBody;
            }

        } catch (APIErrorResponseException e) {
            rootCause = e;
            message = e.getApiResponse().getError().getDetails();
        }

        switch (rCode) {
            case HTTP_BAD_REQUEST:
                throw new InvalidRequestException(message, null, rootCause);
            case HTTP_NOT_FOUND:
                throw new InvalidRequestException(message, null, rootCause);
            case HTTP_UNAUTHORIZED:
                throw new AuthenticationException(message, rootCause);
            default:
                throw new APIException(message, rootCause);
        }
    }

    // GAE requests can time out after 60 seconds, so make sure we leave
    // some time for the application to handle a slow Eligible
    private static final Double GAE_DEADLINE = Double.valueOf(55);

    /*
     * This is slower than usual because of reflection but avoids having to
     * maintain AppEngine-specific JAR
     */
    private static EligibleResponse makeAppEngineRequest(RequestMethod method,
                                                         String url, Map<String, Object> params,
                                                         RequestOptions options)
            throws APIException, InvalidRequestException {
        String unknownErrorMessage = "Sorry, an unknown error occurred while trying to use the "
                + "Google App Engine runtime. Please contact support@eligible.com for assistance.";


        String query;
        try {
            query = createHtmlQuery(params);
        } catch (UnsupportedEncodingException e) {
            throw new InvalidRequestException("Unable to encode parameters to "
                    + CHARSET
                    + ". Please contact support@eligible.com for assistance.",
                    null, e);
        }

        try {
            if (method == RequestMethod.GET || method == RequestMethod.DELETE || method == RequestMethod.PUT) {
                url = format("%s?%s", url, query);
            }
            URL fetchURL = new URL(url);

            Class<?> requestMethodClass = Class
                    .forName("com.google.appengine.api.urlfetch.HTTPMethod");
            Object httpMethod = requestMethodClass.getDeclaredField(
                    method.name()).get(null);

            Class<?> fetchOptionsBuilderClass = Class
                    .forName("com.google.appengine.api.urlfetch.FetchOptions$Builder");
            Object fetchOptions;
            try {
                fetchOptions = fetchOptionsBuilderClass.getDeclaredMethod(
                        "validateCertificate").invoke(null);
            } catch (NoSuchMethodException e) {
                System.err
                        .println("Warning: this App Engine SDK version does not allow verification of SSL certificates;"
                                + "this exposes you to a MITM attack. Please upgrade your App Engine SDK to >=1.5.0. "
                                + "If you have questions, contact support@eligible.com.");
                fetchOptions = fetchOptionsBuilderClass.getDeclaredMethod(
                        "withDefaults").invoke(null);
            }

            Class<?> fetchOptionsClass = Class
                    .forName("com.google.appengine.api.urlfetch.FetchOptions");

            fetchOptionsClass.getDeclaredMethod("setDeadline",
                    Double.class)
                    .invoke(fetchOptions, GAE_DEADLINE);

            Class<?> requestClass = Class
                    .forName("com.google.appengine.api.urlfetch.HTTPRequest");

            Object request = requestClass.getDeclaredConstructor(URL.class,
                    requestMethodClass, fetchOptionsClass).newInstance(
                    fetchURL, httpMethod, fetchOptions);

            if (method == RequestMethod.POST) {
                requestClass.getDeclaredMethod("setPayload", byte[].class)
                        .invoke(request, createJsonPayload(params).getBytes());
            }

            for (Map.Entry<String, String> header : getHeaders(options)
                    .entrySet()) {
                Class<?> httpHeaderClass = Class
                        .forName("com.google.appengine.api.urlfetch.HTTPHeader");
                Object reqHeader = httpHeaderClass.getDeclaredConstructor(
                        String.class, String.class).newInstance(
                        header.getKey(), header.getValue());
                requestClass.getDeclaredMethod("setHeader", httpHeaderClass)
                        .invoke(request, reqHeader);
            }

            Class<?> urlFetchFactoryClass = Class
                    .forName("com.google.appengine.api.urlfetch.URLFetchServiceFactory");
            Object urlFetchService = urlFetchFactoryClass.getDeclaredMethod(
                    "getURLFetchService").invoke(null);

            Method fetchMethod = urlFetchService.getClass().getDeclaredMethod(
                    "fetch", requestClass);
            fetchMethod.setAccessible(true);
            Object response = fetchMethod.invoke(urlFetchService, request);

            int responseCode = (Integer) response.getClass()
                    .getDeclaredMethod("getResponseCode").invoke(response);
            byte[] body = (byte[]) response.getClass().getDeclaredMethod("getContent").invoke(response);
            return new EligibleResponse(responseCode, body);
        } catch (ReflectiveOperationException | MalformedURLException | SecurityException
                | IllegalArgumentException | UnsupportedEncodingException e) {
            throw new APIException(unknownErrorMessage, e);
        }
    }
}
