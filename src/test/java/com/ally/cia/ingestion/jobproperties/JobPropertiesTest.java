package com.ally.cia.ingestion.jobproperties;

import com.ally.cia.ingestion.metadata.job.JobMetadata;
import com.ally.cia.ingestion.metadata.job.JobMetadataProvider;
import com.softwareonpurpose.gauntlet.GauntletTest;
import org.testng.annotations.Test;

@Test(groups={GauntletTest.Application.INGESTION})
public class JobPropertiesTest extends GauntletTest {
    @Test(groups={TestSuite.EVT, TestSuite.DEBUG})
    public void smoke(){
        int jobId = 9;
        JobMetadata jobMetadata = JobMetadataProvider.getInstance().get(jobId);
        JobPropertiesExpected expected = JobPropertiesExpected.getInstance(jobMetadata);
        JobProperties actual = JobProperties.getInstance(jobId);
        then(JobPropertiesCalibrator.getInstance(expected, actual));
    }
}
