package com.lw.spring.feign;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.ws.rs.client.Client;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;


public class CertificateUtil {

    public static SSLContext createSSLContext(String crtFilePath, String keyFilePath, String passphrase) throws Exception {
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        X509Certificate certificate;
        try (InputStream crtInputStream = new FileInputStream(crtFilePath)) {
            certificate = (X509Certificate) cf.generateCertificate(crtInputStream);
        }

        // Load the private key from the .key file and provide a passphrase
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        try (InputStream keyInputStream = new FileInputStream(keyFilePath)) {
            keyStore.load(keyInputStream, passphrase.toCharArray());
        }

        // Create a KeyManagerFactory to manage the private key
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore, passphrase.toCharArray());

        // Create an SSL context and initialize it with the certificate and private key
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(keyManagerFactory.getKeyManagers(), (TrustManager[]) new Certificate[]{certificate}, null);


        //feign.Client cs = new feign.Client.Default(sslContext.getSocketFactory(), (hostname, sslSession) -> true);

        return sslContext;
    }


    public static SSLContext sslContext() throws Exception {
        String crtFilePath = "path_to_certificate.crt";
        String keyFilePath = "path_to_private_key.key";
        String passphrase = "your_passphrase";
        return createSSLContext(crtFilePath, keyFilePath, passphrase);
    }

}


