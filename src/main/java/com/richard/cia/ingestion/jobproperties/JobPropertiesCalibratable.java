package com.richard.cia.ingestion.jobproperties;

import java.util.Set;

public interface JobPropertiesCalibratable {
    Set<String> getPropertyNames();

    String getProperty(String property);

    Integer getPropertyCount();
}
