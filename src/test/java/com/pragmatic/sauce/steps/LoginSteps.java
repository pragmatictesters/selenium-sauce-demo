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
import org.testng.asserts.SoftAssert;

public class LoginSteps {



    private LoginPage loginPage;

    protected WebDriver driver;
    protected SoftAssert softAssert;


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

    @When("the user logs in with username {string} and password {string}")
    public void theUserLogsInWithUsernameAndPassword(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("the user should see an error message {string}")
    public void theUserShouldSeeAnErrorMessage(String expectedError) {
        Assert.assertEquals(loginPage.getError(), expectedError);

    }

    @When("the user logs in with empty username and password")
    public void theUserLogsInWithEmptyUsernameAndPassword() {
        loginPage.login("", "");
    }

    @When("the user logs in with username {string} and empty password")
    public void theUserLogsInWithUsernameAndEmptyPassword(String username) {
        loginPage.login(username, "");
    }

    @When("the user logs in with empty username and password {string}")
    public void theUserLogsInWithEmptyUsernameAndPassword(String password) {
        loginPage.login("", password);

    }

//    @Given("the user is logged into the application")
//    public void theUserIsLoggedIntoTheApplication() {
//        loginPage.login("standard_user", "secret_sauce");
//    }

}