package com.richard.cia.ingestion.metadata.job;

public class JobMetadataProvider {
    public static JobMetadataProvider getInstance() {
        return new JobMetadataProvider();
    }

    public JobMetadata get(int jobId) {
        return JobMetadataRepository.getInstance().query(jobId);
    }
}
