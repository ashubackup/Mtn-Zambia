package com.Scheduler;

import okhttp3.*;
import javax.net.ssl.*;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class TestingCode {

	public static void main(String[] args) throws Exception {
        // Set up the URL and JSON body
        URL url = new URL("https://api.primenetpay.com:9110/api/v1/billers/airtime/purchase");
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("receiverNumber", "260967741119");
        jsonBody.put("externalReference", "783578376857684589");
        jsonBody.put("amount", "1");
        jsonBody.put("accountNumber", "260967741119");

        // Set up the HTTPS connection
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);

        // Send the JSON body
        try (DataOutputStream os = new DataOutputStream(connection.getOutputStream())) {
            byte[] input = jsonBody.toString().getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        // Read the response
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine())!= null) {
                response.append(line);
            }
            System.out.println(response.toString());
        } else {
            System.out.println("Error: " + responseCode);
        }

        // Close the connection
        connection.disconnect();
    }
    
}
