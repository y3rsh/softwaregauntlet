package com.richard.cia.ingestion.metadata.fileattributes;

public class IngestionFileAttributesRepository {
    public static IngestionFileAttributesRepository getInstance() {
        return new IngestionFileAttributesRepository();
    }

    IngestionFileAttributes query(Integer jobId) {
        return IngestionFileAttributes.getInstance(jobId);
    }
}
