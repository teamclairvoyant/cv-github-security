package com.cv;

import java.io.IOException;
import java.util.Map;

public class ListGithubRepoLanguages {
    private ParseUrl parseUrl = new ParseUrl();
    private JsonParser jsonParser = new JsonParser();
    private CSVWriter csvWriter = new CSVWriter();

    private ListGithubRepo listGithubRepo = new ListGithubRepo();

    public static void main(String[] args) throws IOException {
        ListGithubRepoLanguages listGithubRepoLanguages = new ListGithubRepoLanguages();
        listGithubRepoLanguages.getLanguages();
    }

    public void getLanguages() throws IOException {
        Map<String, String> githubRepoList = listGithubRepo.getGithubRepoList();
        jsonParser.getRepoLanguages(githubRepoList, parseUrl);
    }

}
