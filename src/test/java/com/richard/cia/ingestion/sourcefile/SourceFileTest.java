package com.richard.cia.ingestion.sourcefile;

import com.richard.cia.ingestion.metadata.fileattributes.IngestionFileAttributes;
import com.richard.cia.ingestion.metadata.fileattributes.IngestionFileAttributesProvider;
import com.softwareonpurpose.gauntlet.GauntletTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(groups = {GauntletTest.Application.INGESTION})
public class SourceFileTest extends GauntletTest {

    @DataProvider
    public static Object[][] smoke_scenario() {
        return new Object[][]{
                {2}
        };
    }

    @DataProvider
    public static Object[][] specificJob_scenarios() {
        return new Object[][]{
                {9},
                {27}
        };
    }

    @Test(groups = {TestSuite.EVT}, dataProvider = "smoke_scenario")
    public void smoke(Integer jobId) {
        validateSourceFile(jobId);
    }

    @Test(groups = {TestSuite.SPRINT}, dataProvider = "specificJob_scenarios", dependsOnMethods = "smoke")
    public void specificJob(Integer jobId) {
        validateSourceFile(jobId);
    }

    private void validateSourceFile(Integer jobId) {
        SourceFile actualFile = SourceFileProvider.getInstance().get(jobId);
        IngestionFileAttributes expectedSchema = IngestionFileAttributesProvider.getInstance().get(jobId);
        given(String.format("Ingestion Job ID #%d", jobId));
        then(SourceFileCalibrator.getInstance(expectedSchema, actualFile));
    }
}