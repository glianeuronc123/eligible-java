package com.eligible.net;

import javax.net.ssl.X509TrustManager;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * {@link X509TrustManager} implementation for Eligible Certificate Pinning.
 */
public final class PubKeyManager implements X509TrustManager {

    private static final List<String> FINGERPRINTS = new ArrayList<>();
    private static final char[] HEX_DIGITS = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f'
    };
    private static final int FIRST4_BIT_MASK = 0xf0;
    private static final int LAST4_BIT_MASK = 0x0f;
    private static final int BYTE_LENGTH = 4;
    private final MessageDigest md;

    static {
        FINGERPRINTS.add("79d62e8a9d59ae687372f8e71345c76d92527fac");
        FINGERPRINTS.add("4b2c6888ede79d0ee47339dc6fab5a6d0dc3cb0e");
        FINGERPRINTS.add("de4cdd0aae26df71290f0373af18e9ee7ecff18c");
    }

    /**
     * Add fingerprint for a new server.
     *
     * @param fingerprint SHA-1 fingerprint of the certificate.
     */
    public static void addFingerprint(String fingerprint) {
        System.err.println("The embedded certificate fingerprint was modified. "
                + "This should only be done if instructed to by eligible support staff");
        FINGERPRINTS.add(fingerprint);
    }

    /**
     * Create {@link PubKeyManager}.
     *
     * @throws NoSuchAlgorithmException if SHA-1 is not supported
     */
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

        String certFingerprint = getFingerprint(chain[0]);

        for (String fingerprint : FINGERPRINTS) {
            if (fingerprint.equalsIgnoreCase(certFingerprint)) {
                return; // CertPin Success
            }
        }

        throw new CertificateException("checkServerTrusted: Expected public key: "
                + Arrays.toString(FINGERPRINTS.toArray())
                + ", got public key:" + certFingerprint);
    }

    /**
     * Generate SHA-1 fingerprint for {@link X509Certificate}.
     *
     * @param cert Certificate
     * @return fingerprint using SHA-1
     * @throws CertificateEncodingException if an encoding error occurs
     */
    public String getFingerprint(X509Certificate cert) throws CertificateEncodingException {
        // alternate: String certFingerprint = ((X509CertImpl) chain[0]).getFingerprint("SHA-1");
        final byte[] digest = md.digest(cert.getEncoded());
        return byte2hex(digest);
    }

    private static String byte2hex(byte[] bytes) {
        StringBuffer buf = new StringBuffer(bytes.length * 2);

        for (int i = 0; i < bytes.length; ++i) {
            buf.append(HEX_DIGITS[(bytes[i] & FIRST4_BIT_MASK) >> BYTE_LENGTH]);
            buf.append(HEX_DIGITS[bytes[i] & LAST4_BIT_MASK]);
        }

        return buf.toString();
    }

    @Override
    public void checkClientTrusted(X509Certificate[] x509Certificates, String s)
            throws java.security.cert.CertificateException {
        // throw new UnsupportedOperationException("checkClientTrusted: Not supported yet.");
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        // throw new UnsupportedOperationException("getAcceptedIssuers: Not supported yet.");
        return null;
    }
}
