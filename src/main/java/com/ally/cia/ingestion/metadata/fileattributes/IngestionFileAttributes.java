package com.ally.cia.ingestion.metadata.fileattributes;

import java.util.ArrayList;
import java.util.List;

public class IngestionFileAttributes {
    private final ArrayList<String> fieldTypes = new ArrayList<>();

    private IngestionFileAttributes(Integer jobId) {
        if (jobId.equals(9)) {
            fieldTypes.add("int");
        } else {
            fieldTypes.add("INT");
            fieldTypes.add("VARCHAR(10)");
        }
    }

    public static IngestionFileAttributes getInstance(Integer jobId) {
        return new IngestionFileAttributes(jobId);
    }

    public List<String> getFieldTypes() {
        return fieldTypes;
    }
}
