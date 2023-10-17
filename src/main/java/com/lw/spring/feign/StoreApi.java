package com.lw.spring.feign;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import feign.Client;
import feign.Request;
import feign.RequestInterceptor;
import feign.Response;

import javax.net.ssl.SSLContext;
import java.security.cert.X509Certificate;
import java.security.PrivateKey;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.io.ByteArrayInputStream;

import javax.net.ssl.SSLContext;

@FeignClient(value = "storeApiFeign", url = "https://fakestoreapi.com/")
public interface StoreApi {

    @GetMapping("/products")
    JsonNode getProducts();

    @Bean
    public static SSLContext sslContext() throws Exception {
        String crtFilePath = "path_to_certificate.crt";
        String keyFilePath = "path_to_private_key.key";
        String passphrase = "your_passphrase";
        SSLContext sslContext = CertificateUtil.createSSLContext(crtFilePath, keyFilePath, passphrase);
        return sslContext;
    }

    // Create a Feign client bean with a custom Client

}
