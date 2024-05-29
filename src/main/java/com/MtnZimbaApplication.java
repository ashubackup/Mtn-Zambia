package com;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MtnZimbaApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MtnZimbaApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) throws NoSuchAlgorithmException, KeyManagementException {
        // Create a TrustManager that trusts all certificates
        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    // Trust all clients
                }

                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    // Trust all servers
                }
            }
        };

        // Set up an SSL context that uses the above TrustManager
        SSLContext sslContext = SSLContext.getInstance("TLS"); // Use TLS instead of SSL
        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

        // Create an HttpClient that uses the custom SSL context
        CloseableHttpClient httpClient = HttpClients.custom()
            .setSslcontext(sslContext)
            .build();

        // Create a request factory using the custom HttpClient
        HttpComponentsClientHttpRequestFactory customRequestFactory = new HttpComponentsClientHttpRequestFactory();
        customRequestFactory.setHttpClient(httpClient);

        // Build and return the RestTemplate
        return builder.requestFactory(() -> customRequestFactory).build();
    }
}
