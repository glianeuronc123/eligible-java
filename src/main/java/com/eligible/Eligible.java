package com.eligible;

import com.eligible.net.PubKeyManager;
import lombok.Getter;
import lombok.Setter;

import java.net.PasswordAuthentication;
import java.net.Proxy;

/**
 * Eligible Java Bindings.
 * <p>
 * {@link Eligible} class represents basic configurations for making API calls.
 * </p>
 * <pre>
 *   Eligible.apiKey = &lt;api_key&gt;; (default null)
 *   Eligible.isTest = true; (default false)
 *   Eligible.overrideApiBase("https://gds.eligibleapi.com");
 *   Eligible.setConnectionProxy(new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(InetAddress.getLocalHost(), 9905)));
 *   Eligible.setProxyCredential(new PasswordAuthentication("user", "password".toCharArray()));
 * </pre>
 */
public abstract class Eligible {

    /**
     * Eligible API Base URL.
     */
    public static final String LIVE_API_BASE = "https://gds.eligibleapi.com";

    /**
     * Eligible Java Binding version.
     */
    public static final String VERSION = "1.8.2";

    /**
     * Eligible API key for authentication.
     */
    public static volatile String apiKey;

    /**
     * Eligible API version for calls.
     */
    public static volatile String apiVersion = "v1.5";

    /**
     * Eligible API call to simulate sandbox.
     */
    public static volatile boolean isTest;

    @Getter
    private static volatile String apiBase = LIVE_API_BASE;

    /**
     * Connection proxy to tunnel all Eligible connections.
     *
     * @param proxy proxy host and port setting
     * @return {@link Proxy} host and post setting
     */
    @Getter
    @Setter
    private static volatile Proxy connectionProxy = null;

    /**
     * Credential for proxy authorization if required.
     *
     * @param auth proxy required userName and password
     * @return {@link PasswordAuthentication}
     */
    @Getter
    @Setter
    private static volatile PasswordAuthentication proxyCredential = null;


    /**
     * (FOR TESTING ONLY) If you'd like your API requests to hit your own
     * (mocked) server, you can set this up here by overriding the base api URL.
     *
     * @param overriddenApiBase url for eligible api endpoint
     */
    public static void overrideApiBase(final String overriddenApiBase) {
        apiBase = overriddenApiBase;
    }

    /**
     * Add fingerprint of new Eligible Certificate (to be used in Certificate
     * pinning).
     *
     * @param fingerprint SHA-1 digest of certificate
     */
    public static void addFingerprint(String fingerprint) {
        PubKeyManager.addFingerprint(fingerprint);
    }
}
