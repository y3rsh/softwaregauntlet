package com.ally.cia.ingestion.metadata.job;

public class JobMetadataRepository {
    public static JobMetadataRepository getInstance() {
        return new JobMetadataRepository();
    }

    JobMetadata get(int jobId) {
        return JobMetadata.getInstance(jobId);
    }
}
