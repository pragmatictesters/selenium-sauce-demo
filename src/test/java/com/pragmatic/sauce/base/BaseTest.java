package com.pragmatic.sauce.base;

import com.pragmatic.sauce.pages.LoginPage;
import com.pragmatic.sauce.util.BrowserManager;
import com.pragmatic.sauce.util.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import static com.pragmatic.sauce.util.LogManager.*;

import java.lang.reflect.Method;
import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected SoftAssert softAssert;

    @BeforeMethod
    @Parameters({"browser", "headless"})
    public void beforeMethod(@Optional ("chrome") String browser, @Optional("false") boolean headless, Method method) {
        debug("Initializing the browser");
        driver = BrowserManager.getDriver(browser, headless);
        debug("Browser is initialized");
        driver.get(ConfigReader.getProperty("base.url"));
        softAssert = new SoftAssert();
        // If test method name contains 'login', skip login
        if (!method.getName().toLowerCase().contains("login")) login();

    }

    @AfterMethod
    public void afterMethod() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void login() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getProperty("demo.username"),
                ConfigReader.getProperty("demo.password"));
    }
}
