package com.pragmatic.sauce.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = {"src/test/resources/features/sauce-login.feature"},
        glue =  {"com.pragmatic.sauce.steps"},
        monochrome = false,
        tags = "@smoke or @regression",
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
                "json:target/cucumber.json"
        }
)
public class TestRunner2  extends AbstractTestNGCucumberTests {
        //Please keep this area blank
}
