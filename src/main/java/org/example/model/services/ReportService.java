package org.example.model.services;

import org.example.model.entities.Report;
import org.example.model.enums.Status;
import org.example.utilities.ReportStorageSystem;

import java.io.IOException;

public class ReportService {

    private final ReportStorageSystem reportStorageSystem = new ReportStorageSystem();

    public void createReport(Status status, String description) throws IOException {
        Report report = new Report(status, description);
        reportStorageSystem.saveReportToTheSystemFile(report);
    }
}
