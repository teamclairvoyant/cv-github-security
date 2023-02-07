package com.cv;

import lombok.Data;

import java.util.List;
@Data
public class RepositoryInfo {
    private String repositoryUrl;
    private String repoAPIUrl;
    private List<String> languages;
    private List<String> contributors;

    private String name;

    private String description;
}
