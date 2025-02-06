package com.pragmatic.sauce.base;

import com.pragmatic.sauce.pages.LoginPage;
import com.pragmatic.sauce.util.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import static com.pragmatic.sauce.util.Log.*;

import java.lang.reflect.Method;
import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected SoftAssert softAssert;

    @BeforeMethod
    public void beforeMethod(Method method) {
        debug("Initializing the browser");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
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
