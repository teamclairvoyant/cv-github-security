package com.cv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVWriter {
    public void write(List<RepositoryInfo> repositoryInfoList) throws IOException {
        //create a CSV printer
        String fileName = "cv_github_repo_information.csv";
        CSVPrinter printer = new CSVPrinter(new FileWriter(fileName), CSVFormat.DEFAULT);
        //create header row
        printer.printRecord("name","description","repoUrl", "languages", "contributors");
        // create data rows
        for (RepositoryInfo repositoryInfo : repositoryInfoList) {
            printer.printRecord(repositoryInfo.getName(),repositoryInfo.getDescription(),repositoryInfo.getRepositoryUrl(), repositoryInfo.getLanguages(), repositoryInfo.getContributors());
        }
        //close the printer after the file is complete
        printer.flush();
        printer.close();
    }

}
