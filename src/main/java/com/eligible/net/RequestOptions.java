package com.eligible.net;

import com.eligible.Eligible;

public class RequestOptions {
    public static RequestOptions getDefault() {
        return new RequestOptions(Eligible.apiKey, Eligible.apiVersion, Eligible.isTest);
    }

    private final String apiKey;
    private final String eligibleVersion;
    private final boolean test;

    private RequestOptions(String apiKey, String eligibleVersion, boolean test) {
        this.apiKey = apiKey;
        this.eligibleVersion = eligibleVersion;
        this.test = test;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getEligibleVersion() {
        return eligibleVersion;
    }

    public boolean isTest() {
        return test;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RequestOptions that = (RequestOptions) o;

        if (apiKey != null ? !apiKey.equals(that.apiKey) : that.apiKey != null) {
            return false;
        }
        if (test != that.test) {
            return false;
        }
        if (eligibleVersion != null ? !eligibleVersion.equals(that.eligibleVersion) : that.eligibleVersion != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = apiKey != null ? apiKey.hashCode() : 0;
        result = 31 * result + (eligibleVersion != null ? eligibleVersion.hashCode() : 0);
        result = 31 * result + (test ? 1 : 0);
        return result;
    }

    public static RequestOptionsBuilder builder() {
        return new RequestOptionsBuilder();
    }

    public RequestOptionsBuilder toBuilder() {
        return new RequestOptionsBuilder().setApiKey(this.apiKey).setEligibleVersion(this.eligibleVersion).setTest(this.test);
    }

    public static final class RequestOptionsBuilder {
        private String apiKey;
        private String eligibleVersion;
        private boolean test;

        public RequestOptionsBuilder() {
            this.apiKey = Eligible.apiKey;
            this.eligibleVersion = Eligible.apiVersion;
            this.test = Eligible.isTest;
        }

        public String getApiKey() {
            return apiKey;
        }

        public RequestOptionsBuilder setApiKey(String apiKey) {
            this.apiKey = normalizeApiKey(apiKey);
            return this;
        }

        public RequestOptionsBuilder clearApiKey() {
            this.apiKey = null;
            return this;
        }

        public RequestOptionsBuilder setEligibleVersion(String eligibleVersion) {
            this.eligibleVersion = normalizeEligibleVersion(eligibleVersion);
            return this;
        }

        public RequestOptionsBuilder clearEligibleVersion() {
            this.eligibleVersion = null;
            return this;
        }

        public RequestOptionsBuilder setTest(boolean test) {
            this.test = test;
            return this;
        }

        public RequestOptionsBuilder clearTest() {
            this.test = false;
            return this;
        }

        public boolean isTest() {
            return this.test;
        }

        public RequestOptions build() {
            return new RequestOptions(
                    normalizeApiKey(this.apiKey),
                    normalizeEligibleVersion(this.eligibleVersion),
                    this.test);
        }
    }

    private static String normalizeApiKey(String apiKey) {
        // null apiKeys are considered "valid"
        if (apiKey == null) {
            return null;
        }
        String normalized = apiKey.trim();
        if (normalized.isEmpty()) {
            throw new InvalidRequestOptionsException("Empty API key specified!");
        }
        return normalized;
    }

    private static String normalizeEligibleVersion(String eligibleVersion) {
        // null eligibleVersions are considered "valid" and use Eligible.apiVersion
        if (eligibleVersion == null) {
            return null;
        }
        String normalized = eligibleVersion.trim();
        if (normalized.isEmpty()) {
            throw new InvalidRequestOptionsException("Empty Eligible version specified!");
        }
        return normalized;
    }

    private static String normalizeIdempotencyKey(String isTest) {
        if (isTest == null) {
            return null;
        }
        String normalized = isTest.trim();
        if (normalized.isEmpty()) {
            throw new InvalidRequestOptionsException("Empty Idempotency Key Specified!");
        }
        if (normalized.length() > 255) {
            throw new InvalidRequestOptionsException(String.format("Idempotency Key length was %d, which is larger than the 255 character maximum!", normalized.length()));
        }
        return normalized;
    }

    private static String normalizeEligibleAccount(String eligibleAccount) {
        if (eligibleAccount == null) {
            return null;
        }
        String normalized = eligibleAccount.trim();
        if (normalized.isEmpty()) {
            throw new InvalidRequestOptionsException("Empty eligible account specified!");
        }
        return normalized;
    }

    public static class InvalidRequestOptionsException extends RuntimeException {
        public InvalidRequestOptionsException(String message) {
            super(message);
        }
    }
}
