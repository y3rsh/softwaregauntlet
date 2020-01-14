package com.richard.cia.ingestion.metadata.fileattributes;

public class IngestionFileAttributesProvider {
    public static IngestionFileAttributesProvider getInstance() {
        return new IngestionFileAttributesProvider();
    }

    public IngestionFileAttributes get(Integer jobId) {
        return IngestionFileAttributesRepository.getInstance().query(jobId);
    }
}