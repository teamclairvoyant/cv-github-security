package com.clairvoyant;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.*;

public class JsonParser {
    private UrlParser urlParser = new UrlParser();

    public List<RepositoryInfo> getRepositoryInfo(String json) throws IOException {
        JsonElement jsonElement = new com.google.gson.JsonParser().parse(json);
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        List<RepositoryInfo> repositoryInfoList = new LinkedList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonObject = (JsonObject) jsonArray.get(i);
            String repoUrl = getFieldInfo(jsonObject, "html_url");
            String apiUrl = getFieldInfo(jsonObject, "url");
            String name = getFieldInfo(jsonObject, "name");
            String description = getFieldInfo(jsonObject, "description");
            List<String> programmingLanguages = getProgrammingLanguages(apiUrl);
            List<String> contributors = getContributors(apiUrl);
            RepositoryInfo repositoryInfo = setRepositoryInfo(repoUrl, apiUrl, programmingLanguages, contributors, name, description);
            System.out.println(repositoryInfo);
            repositoryInfoList.add(repositoryInfo);
        }
        return repositoryInfoList;
    }

    private RepositoryInfo setRepositoryInfo(String repoUrl, String apiUrl, List<String> programmingLanguages, List<String> contributors, String name, String description) {
        RepositoryInfo repositoryInfo = new RepositoryInfo();
        repositoryInfo.setRepositoryUrl(repoUrl);
        repositoryInfo.setRepoAPIUrl(apiUrl);
        repositoryInfo.setLanguages(programmingLanguages);
        repositoryInfo.setContributors(contributors);
        repositoryInfo.setName(name);
        repositoryInfo.setDescription(description);
        return repositoryInfo;
    }

    private static String getFieldInfo(JsonObject jsonObject, String fieldName) {
        String htmlUrl = jsonObject.get(fieldName).toString();
        htmlUrl = htmlUrl.substring(1, htmlUrl.length() - 1);
        return htmlUrl;
    }

    private List<String> getProgrammingLanguages(String apiUrl) throws IOException {
        apiUrl = apiUrl + "/languages";
        String jsonData = urlParser.getJsonData(apiUrl);
        List<String> languages = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(jsonData);
        Iterator<String> iterator = jsonNode.fieldNames();
        iterator.forEachRemaining(e -> languages.add(e));
        return languages;
    }

    private List<String> getContributors(String apiUrl) throws IOException {
        apiUrl = apiUrl + "/contributors";
        String jsonData = urlParser.getJsonData(apiUrl);
        JsonElement jsonElement = new com.google.gson.JsonParser().parse(jsonData);
        List<String> contributors = new ArrayList<>();
        if(jsonElement.isJsonArray()) {
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonObject = (JsonObject) jsonArray.get(i);
                String login = getFieldInfo(jsonObject, "login");
                contributors.add(login);
            }
        }
        return contributors;
    }

}
