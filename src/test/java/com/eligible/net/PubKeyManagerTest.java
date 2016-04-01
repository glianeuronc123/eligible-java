package com.eligible.net;

import com.eligible.Eligible;
import org.junit.Before;
import org.junit.Test;
import sun.security.x509.CertAndKeyGen;
import sun.security.x509.X500Name;
import sun.security.x509.X509CertImpl;

import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;

import static org.junit.Assert.*;

public class PubKeyManagerTest {

    private PubKeyManager pubKeyManager;

    @Before
    public void setUp() throws Exception {
        pubKeyManager = new PubKeyManager();
    }


    @Test
    public void testAddFingerprint() throws Exception {
        PubKeyManager.addFingerprint("testing");
        // addFingerprint shouldn't throw exception
    }

    @Test()
    public void testCheckServerTrustedMatch() throws Exception {
        X509Certificate cert = generateCert();
        addFingerprint(cert);

        X509Certificate[] chain = {cert};
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
        // addFingerprint shouldn't throw exception
    }

    private void addFingerprint(X509Certificate cert) throws CertificateEncodingException {
        Eligible.addFingerprint(pubKeyManager.getFingerprint(cert));
    }

    private X509Certificate generateCert() throws Exception {
        // generate the certificate
        CertAndKeyGen certGen = new CertAndKeyGen("RSA", "SHA256WithRSA", null);
        certGen.generate(2048); // generate it with 2048 bits

        long validSecs = (long) 365 * 24 * 60 * 60; // valid for one year
        X509Certificate cert = certGen.getSelfCertificate(
                new X500Name("CN=My Application,O=My Organisation,L=My City,C=DE"), // enter your details as per application
                validSecs);
        return cert;
    }

}