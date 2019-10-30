package com.ally.cia.ingestion.sourcefile;

import com.softwareonpurpose.gauntlet.GauntletTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(groups = {GauntletTest.Database.METADATA})
public class SourceFileTest extends GauntletTest {
    @DataProvider
    public static Object[][] jobIds() {
        return new Object[][]{
                {9},
                {27}
        };
    }

    @Test(groups = {TestSuite.EVT})
    public void smoke() {
        int testJobId = 2;
        given(testJobId);
        then(SourceFile.getInstance(testJobId).getCalibrator());
    }

    @Test(groups = {TestSuite.SPRINT}, dataProvider = "jobIds", dependsOnMethods = "smoke")
    public void specificJob(Integer jobId) {
        given(jobId);
        then(SourceFile.getInstance(jobId).getCalibrator());
    }
}
