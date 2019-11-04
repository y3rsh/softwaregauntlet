package com.ally.cia.ingestion.jobproperties;

import com.ally.cia.ingestion.metadata.job.JobMetadata;

import java.util.Properties;
import java.util.Set;

public class JobPropertiesExpected implements JobPropertiesCalibratable {
    private final Properties properties;

    private JobPropertiesExpected(JobMetadata jobMetadata) {
        Properties stagedProperties = new Properties();
        if (((Integer) 9).equals(jobMetadata.getId())) {
            stagedProperties = new Properties();
            stagedProperties.put("job_name", "Job number 9");
            stagedProperties.put("job_id", "9");
        }
        properties = stagedProperties;
    }

    public static JobPropertiesExpected getInstance(JobMetadata jobMetadata) {
        return new JobPropertiesExpected(jobMetadata);
    }

    @Override
    public Set<String> getPropertyNames() {
        return properties.stringPropertyNames();
    }

    @Override
    public String getProperty(String property) {
        return properties.getProperty(property);
    }

    @Override
    public Integer getPropertyCount() {
        return properties.size();
    }
}
