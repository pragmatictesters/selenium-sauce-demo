package com.pragmatic.sauce.tests;

import com.pragmatic.sauce.base.BaseTest;
import com.pragmatic.sauce.pages.LoginPage;
 import  static org.testng.Assert.*;

import com.pragmatic.sauce.pages.ProductsPage;
import com.pragmatic.sauce.util.ConfigReader;
import com.pragmatic.sauce.util.TestDataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginTest extends BaseTest {

    @Test
    public void testLoginWithValidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getProperty("demo.username"),ConfigReader.getProperty("demo.password"));
        ProductsPage productsPage = new ProductsPage(driver);
        assertEquals(productsPage.getTitle(),"Products");
    }

    @Test
    public void testLoginWithBlankUsernameAndBlankPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("","");
        assertEquals(loginPage.getError(),"Epic sadface: Username is required");
    }

    @Test
    public void testLoginWithBlankUsernameAndPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("","secret_sauce");
        assertEquals(loginPage.getError(),"Epic sadface: Username is required");
    }

    @Test
    public void testLoginWithUsernameAndBlankPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user","");
        assertEquals(loginPage.getError(),"Epic sadface: Password is required");
    }

    @Test
    public void testLoginWithInvalidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user","invalidPassword");
        assertEquals(loginPage.getError(),"Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void testCaseSensitivityOfLoginCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user","Secret_sauce");
        assertEquals(loginPage.getError(),"Epic sadface: Username and password do not match any user in this service");
    }


    @Test(dataProviderClass = TestDataProvider.class, dataProvider = "user-credentials")
    public void testLoginWithInvalidCredentialsDataDriven(String username, String password, String expectedMessage) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username,password);
        assertEquals(loginPage.getError(),expectedMessage);
    }

    @Test(dataProviderClass = TestDataProvider.class, dataProvider = "csvUserCredentials")
    public void testLoginWithInvalidCredentialsDDTCSV(String username, String password, String expectedMessage) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username,password);
        assertEquals(loginPage.getError(),expectedMessage);
    }



    @Test
    public void testPlaceholdersInLoginPage() {
        SoftAssert softAssert = new SoftAssert();
        LoginPage loginPage = new LoginPage(driver);
        softAssert.assertEquals(loginPage.getUsernamePlaceholder(), "Username");
        softAssert.assertEquals(loginPage.getPasswordPlaceholder(), "Password");
        softAssert.assertAll();
    }

}
