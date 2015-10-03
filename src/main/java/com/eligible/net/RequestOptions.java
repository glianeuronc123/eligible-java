package com.eligible.net;

import com.eligible.Eligible;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import static com.eligible.util.StringUtil.normalizeString;

/**
 * Request Options to modify individual calls. Request Options can be passed with each method call.
 * <pre>
 *     Payer.all(requestOptions);
 * </pre>
 *
 * Request Options allow you to modify following parameters of an API call.
 * <pre>
 *     RequestOptions.builder()
 *          .setApiKey(&lt;your api key&gt;)
 *          .setApiVersion(Eligible.apiVersion)         // Currently, only v1.5 is supported in java library.
 *          .setTest(true)
 *          .build();
 * </pre>
 */
@Getter
@AllArgsConstructor
public class RequestOptions {
    /**
     * Default values are taken from following variables.
     * <pre>
     *     {@link Eligible}.apiKey
     *     {@link Eligible}.apiVersion
     *     {@link Eligible}.isTest
     * </pre>
     *
     * @return {@link RequestOptions} with defaults set in {@link Eligible}
     */
    public static RequestOptions getDefault() {
        return new RequestOptions(Eligible.apiKey, Eligible.apiVersion, Eligible.isTest);
    }

    /**
     * API key for Eligible API calls.
     * @return apiKey used for authentication
     */
    private final String apiKey;

    /**
     * API version of Eligible API calls. Currently, only v1.5 is supported in java library.
     * @return apiVersion of API calls
     */
    private final String apiVersion;

    /**
     * Change Eligible API calls to sandbox for testing.
     * @return is test API calls enabled
     */
    private final boolean test;

    /**
     * Creates a new {@link com.eligible.net.RequestOptions.RequestOptionsBuilder}.
     *
     * @return builder pattern for {@link RequestOptions}
     */
    public static RequestOptionsBuilder builder() {
        return new RequestOptionsBuilder();
    }

    /**
     * Converts a {@link RequestOptions} to {@link com.eligible.net.RequestOptions.RequestOptionsBuilder}.
     *
     * @return converted {@link com.eligible.net.RequestOptions.RequestOptionsBuilder}
     */
    public RequestOptionsBuilder toBuilder() {
        return new RequestOptionsBuilder(this.apiKey, this.apiVersion, this.test);
    }

    /**
     * Represents builder pattern for {@link RequestOptions}.
     */
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors(chain = true)
    public static final class RequestOptionsBuilder {
        /**
         * API key for Eligible API calls.
         * @param apiKey apiKey for authentication
         */
        private String apiKey = Eligible.apiKey;

        /**
         * API version of Eligible API calls. Currently, only v1.5 is supported in java library.
         * @param apiVersion apiVersion of API calls
         */
        private String apiVersion = Eligible.apiVersion;

        /**
         * Change Eligible API calls to sandbox for testing.
         * @param test test API calls enabled
         */
        private boolean test = Eligible.isTest;

        /**
         * Build Request Options with set values.
         * @return {@link RequestOptions}
         */
        public RequestOptions build() {
            return new RequestOptions(normalizeString(this.apiKey), normalizeString(this.apiVersion), this.test);
        }
    }

}
