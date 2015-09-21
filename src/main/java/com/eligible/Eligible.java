package com.eligible;

import java.net.PasswordAuthentication;
import java.net.Proxy;

/**
 * Eligible Java Bindings.
 * <p/>
 * {@link Eligible} class represents basic configurations for making API calls.
 * <p/>
 * <pre>
 *   Eligible.apiKey = &lt;api_key&gt;; (default null)
 *   Eligible.isTest = true; (default false)
 *   Eligible.overrideApiBase("https://gds.eligibleapi.com");
 *   Eligible.setConnectionProxy(new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(InetAddress.getLocalHost(), 9905)));
 *   Eligible.setProxyCredential(new PasswordAuthentication("user", "password".toCharArray()));
 * </pre>
 */
public abstract class Eligible {
    public static final String LIVE_API_BASE = "https://gds.eligibleapi.com";
    public static final String VERSION = "1.0.0";
    public static volatile String apiKey;
    public static volatile String apiVersion = "v1.5";
    public static volatile boolean isTest;

    private static volatile String apiBase = LIVE_API_BASE;
    private static volatile Proxy connectionProxy = null;
    private static volatile PasswordAuthentication proxyCredential = null;


    /**
     * (FOR TESTING ONLY) If you'd like your API requests to hit your own
     * (mocked) server, you can set this up here by overriding the base api URL.
     */
    public static void overrideApiBase(final String overriddenApiBase) {
        apiBase = overriddenApiBase;
    }

    public static String getApiBase() {
        return apiBase;
    }

    /**
     * Set proxy to tunnel all Eligible connections
     *
     * @param proxy proxy host and port setting
     */
    public static void setConnectionProxy(final Proxy proxy) {
        connectionProxy = proxy;
    }

    /**
     * Get connection proxy
     * @return {@link Proxy} host and post setting
     */
    public static Proxy getConnectionProxy() {
        return connectionProxy;
    }

    /**
     * Provide credential for proxy authorization if required
     *
     * @param auth proxy required userName and password
     */
    public static void setProxyCredential(final PasswordAuthentication auth) {
        proxyCredential = auth;
    }

    /**
     * Get credentials for proxy authorization
     * @return {@link PasswordAuthentication}
     */
    public static PasswordAuthentication getProxyCredential() {
        return proxyCredential;
    }
}
