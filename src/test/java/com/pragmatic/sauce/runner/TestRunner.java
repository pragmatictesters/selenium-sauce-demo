package com.pragmatic.sauce.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = "src/test/resources/features", //Path to feature files
        glue = {"com.pragmatic.sauce.steps"},//Package with step definitions
        plugin = {
                "pretty", //Prints Gherkin steps in the console
                "html:target/cucumber-reports.html", // HTML report
                "json:target/cucumber.json"//JSON report for ext integrations
        },
        monochrome = true // Makes the console output readable
)
public class TestRunner extends AbstractTestNGCucumberTests {
    // No additional methods required
}