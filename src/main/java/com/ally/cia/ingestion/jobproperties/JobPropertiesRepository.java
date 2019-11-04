package com.ally.cia.ingestion.jobproperties;

public class JobPropertiesRepository {
    public static JobPropertiesRepository getInstance() {
        return new JobPropertiesRepository();
    }

    JobProperties query(Integer jobId) {
        return JobProperties.getInstance(jobId);
    }
}
