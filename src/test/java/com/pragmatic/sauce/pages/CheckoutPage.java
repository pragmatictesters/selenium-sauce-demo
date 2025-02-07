package com.pragmatic.sauce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {

    private final WebDriver driver;

    @FindBy(css = "[data-test='title']")
    WebElement title;

    @FindBy(css = "[data-test='firstName']")
    WebElement txtFirstname;

    @FindBy(css = "[data-test='lastName']")
    WebElement txtLastname;

    @FindBy(css = "[data-test='postalCode']")
    WebElement txtPostalCode;

    @FindBy(css = "[data-test='continue']")
    WebElement btnContinue;

    @FindBy(css = "[data-test='cancel']")
    WebElement btnCancel;

    @FindBy(css = "[data-test='error']")
    WebElement msgError;



    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getTitle() {
        return title.getText();
    }

    public CheckoutPage typeFirstname(String firstName) {
        txtFirstname.sendKeys(firstName);
        return this;
    }

    public CheckoutPage typeLastname(String lastName) {
        txtLastname.sendKeys(lastName);
        return this;
    }

    public CheckoutPage typePostalCode(String postalCode) {
        txtPostalCode.sendKeys(postalCode);
        return this;
    }

    public void clickContinue() {
        btnContinue.click();
    }

    public void clickCancel() {
        btnCancel.click();
    }

    public String getError(){
        return msgError.getText();
    }


    public void fillCheckoutInformation(String firstName, String lastName, String postalCode) {
        this.typeFirstname(firstName)
                .typeLastname(lastName)
                .typePostalCode(postalCode)
                .clickContinue();
    }
}
