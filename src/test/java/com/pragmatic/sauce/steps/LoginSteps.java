package com.pragmatic.sauce.steps;

import com.pragmatic.sauce.pages.LoginPage;
import com.pragmatic.sauce.pages.ProductsPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class LoginSteps  {
    private LoginPage loginPage;
    private WebDriver driver;


    @Before
    public void before(){
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
    }

    @After
    public void after(){
        driver.quit();
    }

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        loginPage = new LoginPage(driver);
    }

    @When("the user enters valid credentials")
    public void theUserEntersValidCredentials() {
        loginPage.login("standard_user", "secret_sauce");
    }

    @Then("the user should be redirected to the homepage")
    public void theUserShouldBeRedirectedToTheHomepage() {
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertEquals(productsPage.getTitle(), "Products");
    }
}
