package com.cv;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class ParseUrl {
    String authToken =System.getenv("auth_token");
    public String getJsonData(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        request.addHeader("content-type", "application/json");
        request.addHeader("Authorization",authToken);
        HttpResponse result = httpClient.execute(request);
        return EntityUtils.toString(result.getEntity(), "UTF-8");
    }
}
