package org.example.model.entities;

import org.example.model.enums.Status;

import java.time.LocalDateTime;

public class Report {

    private final Status status;

    private final String description;

    private final LocalDateTime localDateTime;

    public Report(Status status, String description) {
        this.status = status;
        this.description = description;
        this.localDateTime = LocalDateTime.now();
    }


    public Status getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    @Override
    public String toString() {
        return "Report{" +
                "status=" + status +
                ", description='" + description + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
