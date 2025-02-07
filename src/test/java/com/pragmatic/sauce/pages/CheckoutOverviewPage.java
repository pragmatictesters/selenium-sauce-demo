package com.pragmatic.sauce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutOverviewPage {
    private final WebDriver driver;

    @FindBy(css = "[data-test='title']")
    WebElement title;

    @FindBy(css = "[data-test='finish']")
    WebElement btnFinish;

    @FindBy(css = "[data-test='cancel']")
    WebElement btnCancel;

    public CheckoutOverviewPage(WebDriver driver) {
        this.driver= driver;
        PageFactory.initElements(driver, this);
    }

    public String getTitle(){
        return title.getText();
    }

    public void clickFinish(){
        btnFinish.click();
    }

    public void clickCancel(){
        btnCancel.click();
    }

}
