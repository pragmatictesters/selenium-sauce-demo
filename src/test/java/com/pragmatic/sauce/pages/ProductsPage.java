package com.pragmatic.sauce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {

    @FindBy(css = "span[data-test='title']")
    WebElement ttlProducts;


    public ProductsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String getTitle(){
        return ttlProducts.getText();
    }


}
