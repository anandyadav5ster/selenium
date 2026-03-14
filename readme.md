**Core Usages and Features of TestNG**

TestNG (Test Next Generation) is a testing framework inspired by JUnit but designed to be more powerful and easier to use. In Selenium automation, it acts as the "driver" for your test execution.

**1. Test Execution Management (Annotations)**

The primary usage of TestNG is controlling the flow of test execution through annotations.

Pre-conditions: @BeforeSuite, @BeforeTest, @BeforeClass, @BeforeMethod.

Test Logic: @Test.

Post-conditions: @AfterMethod, @AfterClass, @AfterTest, @AfterSuite.

Usage: Ensures the browser opens before a test and closes after, or that a database connection is established once per suite.

**2. Parameterization & Data-Driven Testing**

TestNG eliminates hardcoded values through two main mechanisms:

@Parameters: Pass simple values (like browser type or environment URL) directly from the testng.xml file.

@DataProvider: Pass complex data sets (like 100 sets of login credentials) to a single test method, allowing it to run repeatedly with different data.

**3. Parallel Execution**

TestNG is essential for reducing execution time in large suites.

Usage: By setting parallel="methods" or parallel="classes" in the testng.xml file, you can run multiple tests simultaneously in different browser instances.

**4. Grouping of Tests**

TestNG allows you to categorize your tests into logical groups.

Usage: You can tag tests as @Test(groups = {"Smoke", "Regression"}). During execution, you can choose to run only the "Smoke" group to quickly verify build stability.

**5. Dependency Management**

Sometimes, a test should only run if a previous test passed.

Usage: @Test(dependsOnMethods = {"loginTest"}). If the login fails, the dependent tests are skipped rather than failing individually, which saves time and clarifies reporting.

**6. Prioritization**

By default, TestNG runs tests in alphabetical order.

Usage: @Test(priority = 1). You can assign priority numbers to ensure critical tests run first.

**7. Reporting & Listeners**

TestNG provides built-in HTML reports, but its real power lies in Listeners.

Usage: Implementing ITestListener or IRetryAnalyzer. This is used to capture screenshots automatically on failure, generate custom Extent Reports, or log the start/end of every test case.

**8. Assertions**

TestNG provides a robust set of assertions to validate test results.

Hard Assert: Stops the test execution immediately on failure.

Soft Assert: Collects multiple failures and reports them at the end of the test without stopping the execution flow.

**9. Failure Handling (Rerunning Tests)**

TestNG automatically generates a testng-failed.xml file in the test-output folder, allowing you to rerun only the tests that failed in the previous run without executing the entire suite again.

**10. Invocation Count**

Usage: @Test(invocationCount = 10). This is used for stress testing or stability testing to verify that a specific functionality works consistently across multiple runs.



**Running TestNG via Terminal**

In a Maven-based project, the maven-surefire-plugin is responsible for executing TestNG tests. Below are the most common commands used in professional automation environments.

**1. Standard Maven Commands**

These commands assume you are in the root directory of your project (where pom.xml is located).

Command

Description

mvn test

Runs all tests defined in the src/test/java directory.

mvn clean test

Cleans the target folder first, then runs all tests (Recommended).

mvn test -DsuiteXmlFile=testng.xml

Executes a specific TestNG suite file.

mvn test -Dgroups="Smoke"

Runs only the methods tagged with @Test(groups={"Smoke"}).

mvn test -Dgroups="Smoke,Regression"

Runs multiple groups.

**2. Passing Parameters to Tests**

You can pass custom parameters (like browser type or environment) directly from the terminal using the -D flag.

Example: Run on Chrome

mvn clean test -Dbrowser=chrome


Example: Run a specific test class

mvn test -Dtest=TestOne


**3. Running via Java CLI (No Maven)**

If you aren't using Maven (or need to run compiled .class files directly), you must include the TestNG jar and all dependencies in your classpath (-cp).

# Windows
java -cp "bin;lib/*" org.testng.TestNG testng.xml

# Mac/Linux
java -cp "bin:lib/*" org.testng.TestNG testng.xml


**4. Configuring pom.xml for CLI Suites**

To make mvn test -DsuiteXmlFile=testng.xml work, your pom.xml must be configured to recognize the property:

<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>3.2.5</version>
    <configuration>
        <suiteXmlFiles>
            <!-- This property allows you to pass the filename via terminal -->
            <suiteXmlFile>${suiteXmlFile}</suiteXmlFile>
        </suiteXmlFiles>
    </configuration>
</plugin>
