package com.richard.cia.ingestion.jobproperties;

import com.richard.cia.ingestion.metadata.job.JobMetadata;
import com.richard.cia.ingestion.metadata.job.JobMetadataProvider;
import com.softwareonpurpose.gauntlet.GauntletTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(groups = {GauntletTest.Application.INGESTION})
public class JobPropertiesTest extends GauntletTest {
    @DataProvider
    public static Object[][] jobScenarios() {
        return new Object[][]{{9}};
    }

    @Test(groups = {TestSuite.EVT})
    public void smoke() {
        int jobId = 9;
        JobMetadata jobMetadata = JobMetadataProvider.getInstance().get(jobId);
        JobPropertiesExpected expected = JobPropertiesExpected.getInstance(jobMetadata);
        JobProperties actual = JobPropertiesProvider.getInstance().get(jobId);
        then(JobPropertiesCalibrator.getInstance(expected, actual));
    }

    @Test(groups = {TestSuite.SPRINT}, dependsOnMethods = "smoke", dataProvider = "jobScenarios")
    public void specificJob(Integer jobId) {
        JobMetadata jobMetadata = JobMetadataProvider.getInstance().get(jobId);
        JobPropertiesExpected expected = JobPropertiesExpected.getInstance(jobMetadata);
        JobProperties actual = JobPropertiesProvider.getInstance().get(jobId);
        then(JobPropertiesCalibrator.getInstance(expected, actual));
    }
}
