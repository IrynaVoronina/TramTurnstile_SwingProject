package org.example.utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.model.entities.Report;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ReportStorageSystem {

    private final String fileName = "report";

    public void saveReportToTheSystemFile(Report report) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            Gson gson = new GsonBuilder()
                    //.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                    .setPrettyPrinting()
                    .create();
            gson.toJson(report, writer);
            writer.newLine();
        }
    }
}
