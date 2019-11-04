package com.ally.cia.ingestion.jobproperties;

public class JobPropertiesProvider {
    public static JobPropertiesProvider getInstance() {
        return new JobPropertiesProvider();
    }

    public JobProperties get(Integer jobId) {
        return JobPropertiesRepository.getInstance().query(jobId);
    }
}
