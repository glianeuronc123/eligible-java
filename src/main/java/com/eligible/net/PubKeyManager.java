package com.eligible.net;

import com.sun.xml.internal.xsom.impl.scd.Iterators;

import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/**
 * {@link X509TrustManager} implementation for Eligible Certificate Pinning.
 */
public final class PubKeyManager implements X509TrustManager {

    private static List<String> FINGERPRINTS = new ArrayList<>();
    private static char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private MessageDigest md;

    static {
        FINGERPRINTS.add("79d62e8a9d59ae687372f8e71345c76d92527fac");
        FINGERPRINTS.add("4b2c6888ede79d0ee47339dc6fab5a6d0dc3cb0e");
    }

    public static void addFingerprint(String fingerprint) {
        System.err.println("The embedded certificate fingerprint was modified. This should only be done if instructed to by eligible support staff");
        FINGERPRINTS.add(fingerprint);
    }

    public PubKeyManager() throws NoSuchAlgorithmException {
        md = MessageDigest.getInstance("SHA-1");
    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        if (chain == null) {
            throw new IllegalArgumentException(
                    "checkServerTrusted: X509Certificate array is null");
        }
        if (chain.length == 0) {
            throw new IllegalArgumentException(
                    "checkServerTrusted: X509Certificate is empty");
        }

        // alternate: String certFingerprint = ((X509CertImpl) chain[0]).getFingerprint("SHA-1");

        final byte[] digest = md.digest(chain[0].getEncoded());
        String certFingerprint = byte2hex(digest);

        for (String fingerprint : FINGERPRINTS) {
            if (fingerprint.equalsIgnoreCase(certFingerprint)) {
                return; // CertPin Success
            }
        }

        throw new CertificateException("checkServerTrusted: Expected public key: " + Arrays.toString(FINGERPRINTS.toArray())
                + ", got public key:" + certFingerprint);
    }

    private static String byte2hex(byte bytes[]) {
        StringBuffer buf = new StringBuffer(bytes.length * 2);

        for (int i = 0; i < bytes.length; ++i) {
            buf.append(HEX_DIGITS[(bytes[i] & 0xf0) >> 4]);
            buf.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }

        return buf.toString();
    }

    @Override
    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws java.security.cert.CertificateException {
        // throw new UnsupportedOperationException("checkClientTrusted: Not supported yet.");
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        // throw new UnsupportedOperationException("getAcceptedIssuers: Not supported yet.");
        return null;
    }
}
