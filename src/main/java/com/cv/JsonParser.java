package com.cv;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonParser {
    public Map<String, List<String>> getRepoContributors(Map<String, String> map, ParseUrl parseUrl) throws IOException {
        Map<String, List<String>> repoContributorMap = new HashMap<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String apiUrl = entry.getValue();
            apiUrl = apiUrl + "/contributors";
            String jsonData = parseUrl.getJsonData(apiUrl);
            JsonElement jsonElement = new com.google.gson.JsonParser().parse(jsonData);
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            List<String> contributors = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonObject = (JsonObject) jsonArray.get(i);
                String login = jsonObject.get("login").toString();
                login = login.substring(1, login.length() - 1);
                contributors.add(login);
                repoContributorMap.put(entry.getKey(), contributors);
            }
        }
        return repoContributorMap;
    }

    public Map<String, List<String>> getRepoLanguages(Map<String, String> map, ParseUrl parseUrl) throws IOException {
        Map<String, List<String>> repoContributorMap = new HashMap<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String apiUrl = entry.getValue();
            apiUrl = apiUrl + "/languages";
            String jsonData = parseUrl.getJsonData(apiUrl);
            System.out.println(jsonData);

        }
        return repoContributorMap;
    }
    public Map<String, String> getRepositoryUrl(String json) {
        Map<String, String> repoApiMap = new HashMap<>();
        JsonElement jsonElement = new com.google.gson.JsonParser().parse(json);
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonObject = (JsonObject) jsonArray.get(i);
            String htmlUrl = jsonObject.get("html_url").toString();
            htmlUrl = htmlUrl.substring(1, htmlUrl.length() - 1);
            String apiUrl = jsonObject.get("url").toString();
            apiUrl = apiUrl.substring(1, apiUrl.length() - 1);
            repoApiMap.put(htmlUrl, apiUrl);
        }
        return repoApiMap;
    }

}
