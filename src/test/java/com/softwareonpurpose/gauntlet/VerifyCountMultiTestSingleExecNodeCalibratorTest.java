package com.softwareonpurpose.gauntlet;

import com.softwareonpurpose.gauntlet.anobject.AnObject;
import com.softwareonpurpose.gauntlet.anobject.AnObjectCalibrator;
import com.softwareonpurpose.gauntlet.anobject.AnObjectExpected;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(groups={GauntletTest.TestSuite.UNIT})
public class VerifyCountMultiTestSingleExecNodeCalibratorTest extends GauntletTest {
    public void verificationCount_testMethodOne() {
        AnObjectExpected expectedObject = AnObjectExpected.getInstance();
        AnObject actualObject = AnObject.getInstance();
        AnObjectCalibrator calibrator = AnObjectCalibrator.getInstance(expectedObject, actualObject);
        long expected = 3;
        then(calibrator);
        long actual = getVerificationCount();
        Assert.assertEquals(actual, expected, "FAILED to calculate accurate verification count");
    }

    @Test(dependsOnMethods = "verificationCount_testMethodOne")
    public void verificationCount_testMethodTwo() {
        AnObjectExpected expectedObject = AnObjectExpected.getInstance();
        AnObject actualObject = AnObject.getInstance();
        AnObjectCalibrator calibrator = AnObjectCalibrator.getInstance(expectedObject, actualObject);
        long expected = 6;
        then(calibrator);
        long actual = getVerificationCount();
        Assert.assertEquals(actual, expected, "FAILED to calculate accurate verification count");
    }
}
