package com.pragmatic.sauce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutCompletePage {


    private final WebDriver driver;

    @FindBy(css = "[data-test='title']")
    WebElement title;

    @FindBy(css = "[data-test='complete-header']")
    WebElement completeHeader;

    @FindBy(css = "[data-test='complete-text']")
    WebElement completeMessage;


    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getTitle() {
        return title.getText();
    }

    public String getCompleteHeader() {
        return completeHeader.getText();
    }

    public String getCompleteMessage() {
        return completeMessage.getText();
    }
}
