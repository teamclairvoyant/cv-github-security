package com.cv;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ListGithubRepo {
    private ParseUrl parseUrl = new ParseUrl();
    private JsonParser jsonParser = new JsonParser();
    private CSVWriter csvWriter = new CSVWriter();

    public static void main(String[] args) throws IOException {
        ListGithubRepo listGithubRepo = new ListGithubRepo();
        listGithubRepo.getGithubRepoList();
    }

    public Map<String, String> getGithubRepoList() throws IOException {
        Map<String, String> map = new HashMap<>();
        for (int i = 1; i <= 2; i++) {
            String url = "https://api.github.com/orgs/teamclairvoyant/repos";
            StringBuilder sb = new StringBuilder();
            sb.append("?")
                    .append("page")
                    .append("=")
                    .append(i)
                    .append("&")
                    .append("per_page")
                    .append("=")
                    .append(100);
            url = url + sb;
            map.putAll(parse(url));
        }
        //csvWriter.write(map);
        return map;
    }

    public Map<String, String> parse(String url) {
        Map<String, String> map = null;
        try {
            String json = parseUrl.getJsonData(url);
            map = jsonParser.getRepositoryUrl(json);
            //repoContributors = jsonParser.getRepoContributors(map, parseUrl);
        } catch (IOException ex) {
            System.out.println(ex.getStackTrace());
        }
        return map;
    }
}
