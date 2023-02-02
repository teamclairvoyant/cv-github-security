package com.cv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class CSVWriter {
    public void write(Map<String, String> map) throws IOException {
        //create a CSV printer
        CSVPrinter printer = new CSVPrinter(new FileWriter("github_repos.csv"), CSVFormat.DEFAULT);
        //create header row
        printer.printRecord("repoUrl");
        // create data rows
        for (Map.Entry<String, String> entry : map.entrySet()) {
            printer.printRecord(entry.getKey());
        }
        //close the printer after the file is complete
        printer.flush();
        printer.close();
    }


}
