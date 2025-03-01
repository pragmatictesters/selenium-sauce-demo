package com.pragmatic.sauce.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class SauceLoginSteps {

    private WebDriver webDriver;

    @Before
    public void before(){
        webDriver = new ChromeDriver();
    }


    @After
    public void after(Scenario scenario){
        if(scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "name");
        }
        webDriver.quit();
    }

    @Given("user has accessed the login page")
    public void userHasAccessedTheLoginPage() {
        webDriver.get("https://www.saucedemo.com");
    }

    @When("user provides valid credentials")
    public void userProvidesValidCredentials() {
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
    }

    @Then("the user should be directed to the product inventory page")
    public void theUserShouldBeDirectedToTheProductInventoryPage() {
        String actualTitle = webDriver.findElement(By.cssSelector("[data-test='title']")).getText();
        Assert.assertEquals(actualTitle, "Products");
    }

    @When("user provides invalid credentials {string}, {string}")
    public void userProvidesInvalidCredentials(String username, String password) {
        webDriver.findElement(By.id("user-name")).sendKeys(username);
        webDriver.findElement(By.id("password")).sendKeys(password);
        webDriver.findElement(By.id("login-button")).click();
    }

    @Then("the user should be see error message {string}")
    public void theUserShouldBeSeeErrorMessage(String expectedError) {
        String actualError = webDriver.findElement(By.cssSelector("[data-test='error']")).getText();
        Assert.assertEquals(actualError, expectedError);
    }
}
