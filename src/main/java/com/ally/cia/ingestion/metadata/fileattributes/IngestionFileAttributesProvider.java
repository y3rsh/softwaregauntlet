package com.ally.cia.ingestion.metadata.fileattributes;

public class IngestionFileAttributesProvider {
    public static IngestionFileAttributesProvider getInstance() {
        return new IngestionFileAttributesProvider();
    }

    public IngestionFileAttributes get(Integer jobId) {
        return IngestionFileAttributes.getInstance(jobId);
    }
}