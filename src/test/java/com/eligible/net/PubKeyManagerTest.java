package com.eligible.net;

import org.junit.Before;
import org.junit.Test;

import java.security.cert.X509Certificate;

import static com.eligible.Eligible.addFingerprint;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PubKeyManagerTest {

    private PubKeyManager pubKeyManager;
    private X509Certificate successCert;
    private X509Certificate failureCert;

    @Before
    public void setUp() throws Exception {
        pubKeyManager = new PubKeyManager();
        successCert = mock(X509Certificate.class);
        when(successCert.getEncoded()).thenReturn(new byte[]{0, 1, 2});
        failureCert = mock(X509Certificate.class);
        when(failureCert.getEncoded()).thenReturn(new byte[]{2, 1, 0});
    }


    @Test
    public void testAddFingerprint() throws Exception {
        PubKeyManager.addFingerprint("testing");
        // addFingerprint shouldn't throw exception
    }

    @Test
    public void testCheckServerTrustedMatch() throws Exception {
        addFingerprint("0c7a623fd2bbc05b06423be359e4021d36e721ad");

        X509Certificate[] chain = {successCert};
        pubKeyManager.checkServerTrusted(chain, "ECDHE_RSA");
    }

    @Test(expected = java.security.cert.CertificateException.class)
    public void testCheckServerTrustedNotMatch() throws Exception {
        X509Certificate[] chain = {failureCert};
        pubKeyManager.checkServerTrusted(chain, "ECDHE_RSA");
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void testCheckServerTrustedWithEmpty() throws Exception {
        pubKeyManager.checkServerTrusted(new X509Certificate[0], null);
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void testCheckServerTrustedWithNull() throws Exception {
        pubKeyManager.checkServerTrusted(null, null);
    }

    @Test
    public void testGetAcceptedIssuers() throws Exception {
        assertNull(pubKeyManager.getAcceptedIssuers());
    }

}
