package com.pragmatic.sauce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    @FindBy(id = "user-name")
    private WebElement txtUsername;

    @FindBy(id = "password")
    private WebElement txtPassword;

    @FindBy(id = "login-button")
    private WebElement btnLogin;

    @FindBy(css = "h3[data-test='error']")
    private WebElement msgError;


    public LoginPage typeUsername(String username) {
        txtUsername.sendKeys(username);
        return this;
    }

    public LoginPage typePassword(String password) {
        txtPassword.sendKeys(password);
        return this;
    }

    public void clickLogin() {
        btnLogin.click();
    }

    public String getError() {
        return msgError.getText();
    }

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void login(String username, String password) {
        typeUsername(username).typePassword(password).clickLogin();
    }

    public String getUsernamePlaceholder() {
        return txtUsername.getDomAttribute("placeholder");
    }

    public String getPasswordPlaceholder() {
        return txtPassword.getDomAttribute("placeholder");
    }

}
