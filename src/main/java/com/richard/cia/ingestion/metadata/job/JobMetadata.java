package com.richard.cia.ingestion.metadata.job;

public class JobMetadata {
    private final Integer id;

    private JobMetadata(int jobId) {
        id = jobId;
    }

    public static JobMetadata getInstance(int jobId) {
        return new JobMetadata(jobId);
    }

    public Integer getId() {
        return id;
    }
}
