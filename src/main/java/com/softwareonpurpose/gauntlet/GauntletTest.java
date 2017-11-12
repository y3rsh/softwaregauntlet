/*Copyright 2015 Craig A. Stockton

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.*/
package com.softwareonpurpose.gauntlet;

import com.softwareonpurpose.traceability4test.CoverageReport;
import com.softwareonpurpose.uinavigator.UiHost;
import com.softwareonpurpose.uinavigator.driver.DefaultIeInstantiation;
import com.softwareonpurpose.validator4test.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class GauntletTest {

    private static final int oneSecond = 1000;
    private static final int oneMinute = oneSecond * 60;
    protected static final int defaultTimeout = oneMinute * 1;  //  move the multiplier to a properties file
    private final CoverageReport report;
    private final String className;
    private Logger logger;
    private String testMethodName;
    private String requirements;

    protected GauntletTest() {
        this.className = this.getClass().getSimpleName();
        report = CoverageReport.getInstance(String.format("%s.coverage.rpt", className));
        Validator.setStyle(Validator.ValidationLoggingStyle.BDD);
        initializeUiHost();
    }

    private void initializeUiHost() {
        String browser = System.getProperty("browser");
        if (browser != null) {
            switch (browser) {
                case "ie":
                    UiHost.setDriverInstantiation(DefaultIeInstantiation.getInstance());
                default:
            }
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void beginExecution(Method method) {
        testMethodName = method.getName();
    }

    @AfterMethod(alwaysRun = true)
    public void terminateExecution(ITestResult result) {
        UiHost.quitInstance();
        addCoverageEntry(result);
    }

    private void addCoverageEntry(ITestResult result) {
        List<String> requirementList;
        if (requirements == null) {
            requirementList = Collections.singletonList(null);
        } else {
            requirementList = Arrays.stream(requirements.split("\\|")).collect(Collectors.toList());
        }
        String scenario = result.getParameters().length > 0 ? String.format("|%s", result.getParameters()[0].toString
                ()) : "";
        for (String requirement : requirementList) {
            String entry = requirement == null ? String.format("%s%s", testMethodName, scenario) : String.format
                    ("%s|%s%s", requirement, testMethodName, scenario);
            report.addEntry(entry);
        }
        setRequirements(null);
    }

    @AfterClass
    public void writeCoverageReport() {
        report.write();
    }

    protected void given(Object... testDataDefinitions) {
        String label = "GIVEN";
        for (Object testDataDefinition : testDataDefinitions) {
            getLogger().info(String.format("%s: %s", label, testDataDefinition.toString()));
            label = "  AND";
        }
    }

    protected void when() {
        getLogger().info("");
        getLogger().info("WHEN:");
    }

    protected void then(String testResult) {
        confirm(testResult);
    }

    @SuppressWarnings("WeakerAccess")
    protected void confirm(String testResult) {
        Assert.assertTrue(testResult.equals(Validator.PASS), testResult);
        getLogger().info(String.format("%n==========   '%s' test completed successfully   ==========%n",
                getTestMethodName()));
    }

    private String getTestMethodName() {
        return testMethodName;
    }

    private Logger getLogger() {
        if (logger == null) {
            logger = LoggerFactory.getLogger("");
        }
        return logger;
    }

    private String getTestClass() {
        return className;
    }

    protected void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    protected class TestType {

        public static final String EVT = "evt";                 //  Environment Validation Test
        public static final String DEV = "under_development";   //  Test being developed and/or debugged
        public static final String PRODUCTION = "production";   //  Benign (alters NO source data) test executable in
        // Production
        public static final String RELEASE = "release";         //  Test critical to validating Release Readiness
        public static final String SPRINT = "sprint";           //  Test verifying acceptance criteria for current
        // sprint
    }

    /**
     * Names of Applications under test
     */
    public class Application {
        public static final String THE_INTERNET = "the_internet";

        //  public final static String APPLICATION_NAME = "[application name]";
    }

    /**
     * Names of Views from applications under test
     */
    public class View {
        public static final String CHECKBOXES = "checkboxes";
        public static final String DROPDOWN = "dropdown";
        public static final String DYNAMIC_CONTROLS = "dynamic_controls";

        //  public final static String VIEW_NAME = "[view name]";
    }

    /**
     * Names of Databases supporting applications under test
     */
    public class Database {

        //  public final static String DATABASE_NAME = "[database name]";
    }

    /**
     * Validation targets
     */
    public class Validatee {

        public static final String VIEW = "view";
        public static final String DATA_ENTITY = "[data_entity_name]";
    }

}