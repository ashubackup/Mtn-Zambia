package com.Controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ResolveShortenedUrl {

    public static void main(String[] args) throws IOException {
        String shortenedUrl = "https://maps.app.goo.gl/2xKppZtcJCHn8aR49";
        URL url = new URL(shortenedUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setInstanceFollowRedirects(true);
        connection.connect();

        int statusCode = connection.getResponseCode();
        if (statusCode == HttpURLConnection.HTTP_MOVED_TEMP || statusCode == HttpURLConnection.HTTP_MOVED_PERM) {
            String location = connection.getHeaderField("Location");
            System.out.println("Original URL: " + location);
        } else {
            System.out.println("Failed to resolve shortened URL");
        }
    }
}