package com.clairvoyant;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class UrlParser {
    private String authToken = System.getenv("auth_token");

    public String getJsonData(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        request.addHeader("content-type", "application/json");
        request.addHeader("Authorization", authToken);
        HttpResponse result = httpClient.execute(request);
        HttpEntity httpEntity = result.getEntity();
        if (httpEntity != null) {
            return EntityUtils.toString(httpEntity, "UTF-8");
        } else {
            return "";
        }
    }
}

