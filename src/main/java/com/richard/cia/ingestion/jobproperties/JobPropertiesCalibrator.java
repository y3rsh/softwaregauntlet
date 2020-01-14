package com.richard.cia.ingestion.jobproperties;

import com.softwareonpurpose.calibrator4test.Calibrator;

public class JobPropertiesCalibrator extends Calibrator {
    private static final String DESCRIPTION = "Job Properties";
    private final JobPropertiesExpected expected;
    private final JobProperties actual;

    private JobPropertiesCalibrator(JobPropertiesExpected expected, JobProperties actual) {
        super(DESCRIPTION, expected, actual);
        this.expected = expected;
        this.actual = actual;
    }

    public static JobPropertiesCalibrator getInstance(JobPropertiesExpected expected, JobProperties actual) {
        return new JobPropertiesCalibrator(expected, actual);
    }

    @Override
    protected void executeVerifications() {
        for (String property : expected.getPropertyNames()) {
            verify(property, expected.getProperty(property), actual.getProperty(property));
        }
        for (String property : actual.getPropertyNames()) {
            if (expected.getProperty(property) == null) {
                verify(property, null, actual.getProperty(property));
            }
        }
    }
}
