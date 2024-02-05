package org.example.model.enums;

public enum CardClassification {

    CumulativeCard("cumulative"),
    TransitPass("transit");

    private final String fileName;

    CardClassification(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

}
