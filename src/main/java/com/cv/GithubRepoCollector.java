package com.cv;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GithubRepoCollector {
    private ParseUrl parseUrl = new ParseUrl();
    private JsonParser jsonParser = new JsonParser();

    public static void main(String[] args) throws IOException {
        CSVWriter csvWriter = new CSVWriter();
        GithubRepoCollector githubRepoCollector = new GithubRepoCollector();
        List<RepositoryInfo> repositoryInfoList = githubRepoCollector.getGithubRepoList();
        System.out.println(repositoryInfoList);
        csvWriter.write(repositoryInfoList);
    }

    public List<RepositoryInfo> getGithubRepoList() throws IOException {
        List<RepositoryInfo> repositoryInfoList = new LinkedList<>();
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
            repositoryInfoList.addAll(parse(url));
        }
        return repositoryInfoList;
    }

    public List<RepositoryInfo> parse(String url) {
        try {
            String json = parseUrl.getJsonData(url);
            return jsonParser.getRepositoryInfo(json);
        } catch (IOException ex) {
            System.out.println(ex.getStackTrace());
        }
        return Collections.emptyList();
    }
}
