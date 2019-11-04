package com.ally.cia.ingestion.jobproperties;

import java.util.Properties;
import java.util.Set;

public class JobProperties implements JobPropertiesCalibratable {
    private final Properties properties;

    private JobProperties(int jobId) {
        properties = new Properties();
        properties.put("job_name", "Job number 9");
        properties.put("job_id", "9");
    }

    public static JobProperties getInstance(int jobId) {
        return new JobProperties(jobId);
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
